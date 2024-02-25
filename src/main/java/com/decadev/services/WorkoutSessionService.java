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
        // Implementation assumes Day enum values align with the fitness goal's workout focus
        Map<Day, List<Exercise>> dayToExercisesMap = new EnumMap<>(Day.class);

        // Split exercises by type for more granular control
        List<Exercise> priorityExercises = filterExercisesByType(exercises, ExerciseType.PRIORITY);
        List<Exercise> strengthExercises = filterExercisesByType(exercises, ExerciseType.COMPOUND_STRENGTH);
        List<Exercise> accessoryExercises = filterExercisesByType(exercises, ExerciseType.ACCESSORY);
        List<Exercise> coreExercises = filterExercisesByType(exercises, ExerciseType.CORE);
        List<Exercise> cardioExercises = filterExercisesByType(exercises, ExerciseType.CARDIO);

        // Distribute exercises across days based on fitness goal and level
        for (Day day : Day.values()) {
            List<Exercise> dayExercises = new ArrayList<>();

            // Logic to ensure the appropriate number of exercises based on day focus and user level
            switch (day) {
                case CHEST:
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 3, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 4, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    break;
                case BACK_AND_SHOULDERS:
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 1, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    break;
                case LEGS:
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 3, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 3, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    break;

                case ARMS_CORE:
                    if (fitnessGoal == FitnessGoal.BUILD_MUSCLE) {
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, null, 4, fitnessLevel, Optional.of(BodyPart.ARMS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 3, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    break;
                case PUSH:
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 1, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 2, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                case PULL:
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, accessoryExercises, 3, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    if (fitnessGoal == FitnessGoal.STRENGTH && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(strengthExercises, null, 4, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(accessoryExercises, null, 4, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CORE)));
                    }
                    break;
                case UPPER_BODY: //Weight loss to add an extra priority exercise
                    if (fitnessGoal == FitnessGoal.WEIGHT_LOSS && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 1, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 1, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CORE)));
                        dayExercises.addAll(selectExercisesForDay(cardioExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CARDIO)));
                    }
                    if (fitnessGoal == FitnessGoal.WEIGHT_LOSS && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.BACK)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 2, fitnessLevel, Optional.of(BodyPart.CHEST)));
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 1, fitnessLevel, Optional.of(BodyPart.SHOULDERS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CORE)));
                        dayExercises.addAll(selectExercisesForDay(cardioExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CARDIO)));
                    }
                    break;
                case LOWER_BODY:
                    if (fitnessGoal == FitnessGoal.WEIGHT_LOSS && (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 3, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CORE)));
                        dayExercises.addAll(selectExercisesForDay(cardioExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CARDIO)));
                    }
                    if (fitnessGoal == FitnessGoal.WEIGHT_LOSS && (fitnessLevel == FitnessLevel.ADVANCED || fitnessLevel == FitnessLevel.EXPERT)) {
                        Collections.shuffle(coreExercises);
                        dayExercises.addAll(selectExercisesForDay(priorityExercises, accessoryExercises, 4, fitnessLevel, Optional.of(BodyPart.LEGS)));
                        dayExercises.addAll(selectExercisesForDay(coreExercises, null, 2, fitnessLevel, Optional.of(BodyPart.CORE)));
                        dayExercises.addAll(selectExercisesForDay(cardioExercises, null, 1, fitnessLevel, Optional.of(BodyPart.CARDIO)));
                    }
                    break;
            }

            if (!dayExercises.isEmpty()) {
                dayToExercisesMap.put(day, dayExercises);
            }
        }

        return dayToExercisesMap;
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
        int availableHours = user.getAvailability(); // Get the user's availability in hours

        // Limit the number of sessions based on the user's availability
        dayToExercisesMap.entrySet().stream()
                .limit(availableHours) // Use the user's availability to limit the number of days/sessions
                .forEach(entry -> {
                    Day day = entry.getKey();
                    List<Exercise> exercises = entry.getValue();

                    WorkoutSession session = new WorkoutSession();
                    session.setSessionId(UUID.randomUUID().toString());
                    session.setUserId(user.getUserId());
                    session.setDay(day);
                    session.setExercises(exercises);
                    session.setSessionDuration(Duration.ofHours(1)); // Each session is 1 hour
                    sessions.add(session);
                });

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