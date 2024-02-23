package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.enums.*;
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
        List<Day> workoutDays = determineWorkoutDays(user.getFitnessGoal(), calculateSessionsPerWeek(user.getAvailability()));

        for (Day day : workoutDays) {
            // Generate session exercises based on the user's goal, level, and day
            List<Exercise> sessionExercises = generateSessionExercises(user.getFitnessLevel(), user.getFitnessGoal(), day);

            WorkoutSession session = new WorkoutSession();
            session.setUserId(user.getUserId());
            session.setDay(day);
            session.setExercises(sessionExercises);
            session.setSessionDuration(Duration.ofHours(1)); // Standardized to 1 hour

            sessions.add(workoutSessionRepository.save(session));
        }

        return sessions;
    }

    private List<Exercise> generateSessionExercises(FitnessLevel level, FitnessGoal goal, Day day) {
        List<Exercise> allExercises = exerciseService.getCustomizedExercisesForUser(level, goal);
        List<Exercise> sessionExercises = filterExercisesByDay(allExercises, day, goal);

        // Adjust session composition based on the goal and fitness level
        return adjustSessionComposition(sessionExercises, level, goal);
    }

    private List<Exercise> adjustSessionComposition(List<Exercise> exercises, FitnessLevel level, FitnessGoal goal) {
        // This method would prioritize exercises, manage MRV, and ensure a core exercise is included.
        // Implementation would depend on the specific logic for MRV, priority handling, and core inclusion.
        // Placeholder for actual logic.
        return exercises; // Adjusted list based on MRV and priorities.
    }
    /**
     * Calculates the number of sessions per week based on user availability. The minimum availability is 2 hours per week.
     * @param availability the user's availability in hours per week
     * @return the calculated number of sessions per week
     */
    private int calculateSessionsPerWeek(Integer availability) {
        return Math.max(Optional.ofNullable(availability).orElse(2), 2);
    }

    /**
     * Determines the focus day for a workout session based on the user's fitness goal and the session index.
     * @param goal the user's fitness goal
     * @return the day focus of the workout session
     */
    private List<Day> determineWorkoutDays(FitnessGoal goal, int sessionsCount) {
        // Logic adjusted for clarity and direct alignment with the goals without fallbacks for undefined goals
        switch (goal) {
            case BUILD_MUSCLE -> {
                return Arrays.asList(Day.CHEST, Day.BACK_AND_SHOULDERS, Day.LEGS, Day.ARMS_CORE);
            }
            case STRENGTH -> {
                return Arrays.asList(Day.PUSH, Day.PULL, Day.LEGS);
            }
            case WEIGHT_LOSS -> {
                return Arrays.asList(Day.UPPER_BODY, Day.LOWER_BODY);
            }
            default -> throw new IllegalArgumentException("Unexpected fitness goal: " + goal);
        }
    }

    /**
     * Filters exercises based on the workout day and fitness goal.
     * @param exercises the list of exercises to filter
     * @param day the focus day for the workout session
     * @param goal the user's fitness goal
     * @return a list of filtered exercises
     */
    public List<Exercise> filterExercisesByDay(List<Exercise> exercises, Day day, FitnessGoal goal) {
        Set<String> relevantBodyParts = switch (day) {
            case BACK_AND_SHOULDERS -> Set.of("Back", "Shoulders");
            case LEGS -> Set.of("Legs");
            case CHEST -> Set.of("Chest");
            case ARMS_CORE -> Set.of("Arms", "Core"); // For BUILD_MUSCLE, includes core exercises specifically on this day
            case PUSH -> Set.of("Chest", "Shoulders", "Triceps"); // For STRENGTH, focusing on push exercises
            case PULL -> Set.of("Back", "Biceps", "Forearms"); // For STRENGTH, focusing on pull exercises
            case UPPER_BODY -> goal == FitnessGoal.WEIGHT_LOSS ? Set.of("Cardio", "Upper Body") : Set.of("Back", "Chest", "Shoulders", "Arms");
            case LOWER_BODY -> Set.of("Legs", "Cardio");
        };

        List<Exercise> filteredExercises = exercises.stream()
                .filter(e -> relevantBodyParts.contains(e.getBodyPart()))
                .collect(Collectors.toList());

        // For WEIGHT_LOSS, ensure at least one cardio exercise is included
        if (goal == FitnessGoal.WEIGHT_LOSS && !day.equals(Day.UPPER_BODY) && !day.equals(Day.LOWER_BODY)) {
            filteredExercises.addAll(exercises.stream()
                    .filter(e -> e.getExerciseType() == ExerciseType.CARDIO)
                    .limit(1) // Ensure only one cardio exercise is added if not already included based on day
                    .collect(Collectors.toList()));
        }

        return filteredExercises;
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