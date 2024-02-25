package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.enums.*;
import com.decadev.repositories.WorkoutSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkoutSessionService {
    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private ExerciseService exerciseService;

    /**
     * Constructs a WorkoutSessionService with the necessary dependencies.
     * @param workoutSessionRepository the repository for workout sessions
     * @param exerciseService the service for managing exercises
     */
    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, ExerciseService exerciseService) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseService = exerciseService;
    }
//TODO: need to make sure corresponding days for goal are mapped for individual workoutsessions along with relevant exercises for body part with count limit for priority, strength, core and  cardio
    public List<WorkoutSession> generateWorkoutSessionsForUser(User user) {
        List<Exercise> customizedExercises = exerciseService.getCustomizedExercisesForUser(user.getFitnessLevel(), user.getFitnessGoal());
        Map<Day, List<Exercise>> dayToExercisesMap = mapSessionsToDays(customizedExercises, user.getFitnessGoal(), user.getFitnessLevel());

        if (user.getFitnessGoal() == FitnessGoal.WEIGHT_LOSS) {
            // Separate lists for core and cardio exercises, assuming they are part of customizedExercises
            List<Exercise> coreExercises = customizedExercises.stream()
                    .filter(e -> e.getExerciseType() == ExerciseType.CORE)
                    .collect(Collectors.toList());
            List<Exercise> cardioExercises = customizedExercises.stream()
                    .filter(e -> e.getExerciseType() == ExerciseType.CARDIO)
                    .collect(Collectors.toList());

            rotateExercisesForVariety(dayToExercisesMap, coreExercises, cardioExercises, user.getFitnessLevel());
        }

        // Pass the User object instead of just userId
        return buildWorkoutSessions(dayToExercisesMap, user);
    }

    private Map<Day, List<Exercise>> mapSessionsToDays(List<Exercise> exercises, FitnessGoal fitnessGoal, FitnessLevel fitnessLevel) {
        Map<Day, List<Exercise>> dayToExercisesMap = new EnumMap<>(Day.class);

        // Shuffle core and cardio exercises for variety
        Collections.shuffle(filterExercisesByType(exercises, ExerciseType.CORE));
        Collections.shuffle(filterExercisesByType(exercises, ExerciseType.CARDIO));

        for (Day day : Day.values()) {
            List<Exercise> dayExercises = new ArrayList<>();

            // Unified counts for Beginner & Intermediate, and Advanced & Expert
            int priorityCount = (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE) ? 3 : 4;
            int accessoryCount = 4; // Consistent for all levels
            int coreCount = (day == Day.ARMS_CORE || day == Day.UPPER_BODY || day == Day.LOWER_BODY) ? 2 : 1;
            int cardioCount = (fitnessGoal == FitnessGoal.WEIGHT_LOSS) ? 1 : 0; // For WEIGHT_LOSS goal

            // Adjusting selection based on the fitness goal and day
            switch (fitnessGoal) {
                case BUILD_MUSCLE:
                    // Specific body part targeting for BUILD_MUSCLE days
                    if (day != Day.PUSH && day != Day.PULL) { // Exclude PUSH and PULL for BUILD_MUSCLE
                        Optional<BodyPart> targetBodyPart = mapDayToBodyPart(day);
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.PRIORITY), filterExercisesByType(exercises, ExerciseType.ACCESSORY), priorityCount, fitnessLevel, targetBodyPart));
                        if (day != Day.ARMS_CORE) { // Accessory exercises for non-ARMS_CORE days
                            dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.ACCESSORY), null, accessoryCount, fitnessLevel, targetBodyPart));
                        }
                        // Core exercises for all BUILD_MUSCLE days
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.CORE), null, coreCount, fitnessLevel, Optional.empty()));
                    }
                    break;
                case STRENGTH:
                    // STRENGTH days involve full body, hence no specific body part targeting here
                    if (day == Day.PUSH || day == Day.PULL || day == Day.LEGS) {
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.COMPOUND_STRENGTH), null, priorityCount, fitnessLevel, Optional.empty()));
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.ACCESSORY), null, accessoryCount, fitnessLevel, Optional.empty()));
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.CORE), null, coreCount, fitnessLevel, Optional.empty()));
                    }
                    break;
                case WEIGHT_LOSS:
                    // WEIGHT_LOSS days focus on cardio alongside priority exercises
                    dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.PRIORITY), null, priorityCount++, fitnessLevel, mapDayToBodyPart(day)));
                    dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.CORE), null, coreCount, fitnessLevel, Optional.empty()));
                    if (cardioCount > 0) {
                        dayExercises.addAll(selectExercisesForDay(filterExercisesByType(exercises, ExerciseType.CARDIO), null, cardioCount, fitnessLevel, Optional.empty()));
                    }
                    break;
            }

            if (!dayExercises.isEmpty()) {
                dayToExercisesMap.put(day, dayExercises);
            }
        }

        return dayToExercisesMap;
    }

    private Optional<BodyPart> mapDayToBodyPart(Day day) {
        switch (day) {
            case CHEST: return Optional.of(BodyPart.CHEST);
            case BACK_AND_SHOULDERS: return Optional.of(BodyPart.BACK, BodyPart.SHOULDERS); // Simplification, assumes BACK focus
            case LEGS: return Optional.of(BodyPart.LEGS);
            case ARMS_CORE: return Optional.of(BodyPart.ARMS);
            case PULL: return Optional.of(BodyPart.BACK);
            case PUSH: return Optional.of(BodyPart.CHEST && BodyPart.SHOULDERS);
            case UPPER_BODY: return Optional.of(BodyPart.CHEST && BodyPart.BACK  && BodyPart.SHOULDERS);
            case LOWER_BODY:
            // Add mappings for other days if necessary
            default: return Optional.empty();
        }
    }

    private void rotateExercisesForVariety(Map<Day, List<Exercise>> dayToExercisesMap, List<Exercise> coreExercises, List<Exercise> cardioExercises, FitnessLevel fitnessLevel) {
        // Determine the number of upper and lower body sessions to decide if rotation is needed
        long upperBodyCount = dayToExercisesMap.keySet().stream().filter(day -> day == Day.UPPER_BODY).count();
        long lowerBodyCount = dayToExercisesMap.keySet().stream().filter(day -> day == Day.LOWER_BODY).count();

        // Initialize indices for core and cardio exercises
        int coreIndex = 0, cardioIndex = 0;

        for (Map.Entry<Day, List<Exercise>> entry : dayToExercisesMap.entrySet()) {
            if ((entry.getKey() == Day.UPPER_BODY && upperBodyCount > 2) || (entry.getKey() == Day.LOWER_BODY && lowerBodyCount > 2)) {
                List<Exercise> sessionExercises = entry.getValue();
                // Perform rotation only if there are enough exercises to rotate
                if (!coreExercises.isEmpty() && !cardioExercises.isEmpty()) {
                    // Remove existing core and cardio exercises
                    sessionExercises.removeIf(e -> e.getExerciseType() == ExerciseType.CORE || e.getExerciseType() == ExerciseType.CARDIO);

                    // Add next core and cardio exercises in line for variety
                    sessionExercises.add(coreExercises.get(coreIndex % coreExercises.size()));
                    sessionExercises.add(cardioExercises.get(cardioIndex % cardioExercises.size()));

                    // Increment indices for next rotation
                    coreIndex++;
                    cardioIndex++;
                }
            }
        }
    }

    private List<Exercise> selectExercisesForDay(List<Exercise> primaryList, List<Exercise> secondaryList, int count, FitnessLevel level, Optional<BodyPart> optionalBodyPart) {
        // Filter primary list by optional body part if present
        Stream<Exercise> primaryStream = optionalBodyPart.isPresent() ?
                primaryList.stream().filter(e -> e.getBodyPart() == optionalBodyPart.get()) :
                primaryList.stream();

        List<Exercise> selected = primaryStream.limit(count).collect(Collectors.toList());

        // If not enough exercises are selected and a secondary list is provided
        if (selected.size() < count && secondaryList != null) {
            Stream<Exercise> secondaryStream = optionalBodyPart.isPresent() ?
                    secondaryList.stream().filter(e -> e.getBodyPart() == optionalBodyPart.get()) :
                    secondaryList.stream();

            selected.addAll(secondaryStream.limit(count - selected.size()).collect(Collectors.toList()));
        }

        return selected;
    }

    private List<Exercise> filterExercisesByType(List<Exercise> exercises, ExerciseType type) {
        return exercises.stream().filter(e -> e.getExerciseType() == type).collect(Collectors.toList());
    }

    private List<WorkoutSession> buildWorkoutSessions(Map<Day, List<Exercise>> dayToExercisesMap, User user) {
        List<WorkoutSession> sessions = new ArrayList<>();
        int availableHours = user.getAvailability();

        // Calculate iterations needed to fill all available hours with the variety of workout days
        int iterationsNeeded = (int) Math.ceil((double) availableHours / dayToExercisesMap.size());
        List<Day> workoutDaysRepeated = new ArrayList<>();

        // Repeat workout days to match availability
        for (int i = 0; i < iterationsNeeded; i++) {
            workoutDaysRepeated.addAll(dayToExercisesMap.keySet());
        }

        // Limit to the user's actual availability
        workoutDaysRepeated = workoutDaysRepeated.subList(0, Math.min(workoutDaysRepeated.size(), availableHours));

        System.out.println("Day Map Size: " + dayToExercisesMap.size());
        System.out.println("User Availability: " + availableHours);

        for (Day day : workoutDaysRepeated) {
            List<Exercise> exercises = dayToExercisesMap.get(day);
            WorkoutSession session = new WorkoutSession();
            session.setSessionId(UUID.randomUUID().toString());
            session.setUserId(user.getUserId());
            session.setDay(day);
            session.setExercises(exercises);
            session.setSessionDuration(Duration.ofHours(1));
            sessions.add(session);
        }

        return sessions;
    }
    /**
     * Retrieves a workout session by its unique identifier.
     *
     * @param sessionId the unique ID of the workout session.
     * @return an Optional containing the found workout session or an empty Optional if not found.
     */
    public Optional<WorkoutSession> findWorkoutSessionById(String sessionId) {
        return workoutSessionRepository.findById(sessionId);
    }



    /**
     * Deletes a workout session from the repository by its unique identifier.
     *
     * @param sessionId the unique ID of the workout session to be deleted.
     */
    public void deleteWorkoutSessionById(String sessionId) {
        workoutSessionRepository.deleteById(sessionId);
    }
}