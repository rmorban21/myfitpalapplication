package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.repositories.WorkoutSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Generates workout sessions for a user based on their availability and fitness goals.
     * @param user the user for whom to generate workout sessions
     * @return a list of generated workout sessions
     */
    public List<WorkoutSession> generateWorkoutSessions(User user) {
        List<WorkoutSession> sessions = new ArrayList<>();
        int sessionsCount = calculateSessionsPerWeek(user.getAvailability(), user.getFitnessGoal());

        for (int i = 0; i < sessionsCount; i++) {
            Day day = determineWorkoutDay(user.getFitnessGoal(), i);
            List<Exercise> sessionExercises = getCustomizedExercises(user, day);
            Duration sessionDuration = determineSessionDuration(user.getFitnessLevel(), user.getAvailability());

            WorkoutSession session = new WorkoutSession();
            session.setUserId(user.getUserId());
            session.setDay(day);
            session.setExercises(sessionExercises);
            session.setSessionDuration(sessionDuration);

            workoutSessionRepository.save(session);
            sessions.add(session);
        }

        return sessions;
    }

    /**
     * Calculates the number of sessions per week based on user availability and fitness goals.
     * @param availability the user's availability in hours per week
     * @param goal the user's fitness goal
     * @return the calculated number of sessions per week
     */
    public int calculateSessionsPerWeek(Integer availability, FitnessGoal goal) {
        int baseSessionCount = Math.max(2, availability / 2); // Ensures a minimum of 2 sessions
        return switch (goal) {
            case BUILD_MUSCLE -> Math.min(baseSessionCount, 5);
            case WEIGHT_LOSS -> Math.min(baseSessionCount, 4);
            case STRENGTH -> Math.min(baseSessionCount, 3);
            default -> baseSessionCount;
        };
    }

    /**
     * Determines the duration of each workout session based on the user's fitness level and availability.
     * @param fitnessLevel the user's fitness level
     * @param availability the user's availability in hours per week
     * @return the duration of each workout session
     */
    private Duration determineSessionDuration(FitnessLevel fitnessLevel, Integer availability) {
        return switch (fitnessLevel) {
            case BEGINNER -> Duration.ofMinutes(45);
            default -> availability >= 4 ? Duration.ofMinutes(60) : Duration.ofMinutes(30);
        };
    }

    /**
     * Determines the focus day for a workout session based on the user's fitness goal and the session index.
     * @param fitnessGoal the user's fitness goal
     * @param sessionIndex the index of the session in the workout plan
     * @return the day focus of the workout session
     */
    private Day determineWorkoutDay(FitnessGoal fitnessGoal, int sessionIndex) {
        var daysMapping = Map.of(
                FitnessGoal.BUILD_MUSCLE, Arrays.asList(Day.CHEST, Day.BACK_AND_SHOULDERS, Day.LEGS, Day.LEGS_ARMS),
                FitnessGoal.WEIGHT_LOSS, Arrays.asList(Day.UPPER_BODY, Day.LOWER_BODY),
                FitnessGoal.STRENGTH, Arrays.asList(Day.BACK_AND_SHOULDERS, Day.LEGS, Day.CHEST)
        );

        List<Day> days = daysMapping.getOrDefault(fitnessGoal, Collections.singletonList(Day.UPPER_BODY));
        return days.get(sessionIndex % days.size());
    }

    /**
     * Gets customized exercises for a user based on the day's focus and the user's profile.
     * @param user the user for whom to customize exercises
     * @param day the focus day for the workout session
     * @return a list of customized exercises
     */
    private List<Exercise> getCustomizedExercises(User user, Day day) {
        List<Exercise> filteredExercises = exerciseService.getAllExercises().stream()
                .filter(e -> exerciseService.isEquipmentAccessible(user.getGymAccess(), e.getEquipment()) && e.getFitnessLevel().compareTo(user.getFitnessLevel()) <= 0)
                .collect(Collectors.toList());

        return filterExercisesByDay(filteredExercises, day, user.getFitnessGoal());
    }

    /**
     * Filters exercises based on the workout day and fitness goal.
     * @param exercises the list of exercises to filter
     * @param day the focus day for the workout session
     * @param goal the user's fitness goal
     * @return a list of filtered exercises
     */
    public List<Exercise> filterExercisesByDay(List<Exercise> exercises, Day day, FitnessGoal goal) {
        // Filters exercises based on the workout day and fitness goal
        Set<String> relevantBodyParts = switch (day) {
            case BACK_AND_SHOULDERS -> Set.of("Back", "Shoulders");
            case LEGS -> Set.of("Legs");
            case CHEST -> Set.of("Chest");
            case LEGS_ARMS -> Set.of("Legs", "Arms");
            case UPPER_BODY -> goal == FitnessGoal.WEIGHT_LOSS ? Set.of("Cardio", "Upper Body") : Set.of("Back", "Chest", "Shoulders");
            case LOWER_BODY -> Set.of("Legs");
        };

        return exercises.stream()
                .filter(e -> relevantBodyParts.contains(e.getBodyPart()) && (goal != FitnessGoal.WEIGHT_LOSS || e.getExerciseType() == ExerciseType.CARDIO || e.getExerciseType() == ExerciseType.COMPOUND))
                .collect(Collectors.toList());
    }

    /**
     * Filters exercises based on body part and gym access.
     * @param bodyParts the list of body parts to filter by
     * @param exercises the list of exercises to filter
     * @param gymAccess the user's gym access level
     * @return a list of filtered exercises
     */
    public List<Exercise> filterExercisesByBodyPart(List<String> bodyParts, List<Exercise> exercises, GymAccess gymAccess) {
        return exercises.stream()
                .filter(e -> bodyParts.contains(e.getBodyPart()))
                .filter(e -> exerciseService.isEquipmentAccessible(gymAccess, e.getEquipment()))
                .collect(Collectors.toList());
    }

    /**
     * Filters exercises based on the user's fitness level.
     * @param exercises the list of exercises to filter
     * @param userFitnessLevel the fitness level of the user
     * @return a list of filtered exercises
     */
    public List<Exercise> filterExercisesByFitnessLevel(List<Exercise> exercises, FitnessLevel userFitnessLevel) {
        if (exercises == null || userFitnessLevel == null) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }

        return exercises.stream()
                .filter(exercise -> exercise != null && exercise.getFitnessLevel() != null && exercise.getFitnessLevel().compareTo(userFitnessLevel) <= 0)
                .collect(Collectors.toList());
    }

    /**
     * Filters exercises based on the user's gym access.
     * @param gymAccess the gym access level of the user
     * @return a list of exercises accessible to the user
     */
    public List<Exercise> filterExercisesByGymAccess(GymAccess gymAccess) {
        if (gymAccess == null) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }

        List<Exercise> exercises = exerciseService.getAllExercises(); // Obtain the list of exercises from ExerciseService
        return exercises.stream()
                .filter(exercise -> {
                    switch (gymAccess) {
                        case HOME_GYM_NO_WEIGHTS:
                            return "None".equalsIgnoreCase(exercise.getEquipment());
                        case HOME_GYM_WITH_WEIGHTS:
                            return "None".equalsIgnoreCase(exercise.getEquipment()) || "Dumbbells".equalsIgnoreCase(exercise.getEquipment()) || "Foam Roller".equalsIgnoreCase(exercise.getEquipment());
                        case FULL_GYM_ACCESS:
                            return true;
                        default:
                            return false; // Ensuring all paths return a value
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Finds a workout session by its ID.
     * @param sessionId the ID of the workout session
     * @return an Optional containing the workout session if found
     */
    public Optional<WorkoutSession> findWorkoutSessionById(String sessionId) {
        return workoutSessionRepository.findById(sessionId);
    }

    /**
     * Deletes a workout session by its ID.
     * @param sessionId the ID of the workout session to delete
     */
    public void deleteWorkoutSessionById(String sessionId) {
        workoutSessionRepository.deleteById(sessionId);
    }
}