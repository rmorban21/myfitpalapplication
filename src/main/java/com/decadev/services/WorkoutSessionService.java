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

    //TODO: need to revist workout generation logic as this now creates different days but only populates the day with
    // one relevant exercise and created only 2 workout sessions for a user with 5 hours of availability (beginner)
    /**
     * Generates workout sessions for a user based on their availability and fitness goals.
     * @param user the user for whom to generate workout sessions
     * @return a list of generated workout sessions
     */
    public List<WorkoutSession> generateWorkoutSessions(User user) {
        List<WorkoutSession> sessions = new ArrayList<>();
        int sessionsCount = calculateSessionsPerWeek(user.getAvailability());
        List<Day> workoutDays = determineWorkoutDays(user.getFitnessGoal());

        // Fetch all customized exercises once to avoid multiple calls
        List<Exercise> allCustomizedExercises = exerciseService.getCustomizedExercisesForUser(user.getFitnessLevel(), user.getFitnessGoal());

        for (int i = 0; i < sessionsCount; i++) {
            Day day = workoutDays.get(i % workoutDays.size());

            // Refine exercises for each session based on the day's focus
            List<Exercise> sessionExercises = filterExercisesByDay(allCustomizedExercises, day, user.getFitnessGoal());

            WorkoutSession session = new WorkoutSession();
            session.setUserId(user.getUserId());
            session.setDay(day);
            session.setExercises(sessionExercises);
            session.setSessionDuration(Duration.ofHours(1)); // Keep the session duration standardized to 1 hour

            sessions.add(workoutSessionRepository.save(session));
        }

        return sessions;
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
     * @param fitnessGoal the user's fitness goal
     * @return the day focus of the workout session
     */
    private List<Day> determineWorkoutDays(FitnessGoal fitnessGoal) {
        return switch (fitnessGoal) {
            case BUILD_MUSCLE -> Arrays.asList(Day.CHEST, Day.BACK_AND_SHOULDERS, Day.LEGS, Day.LEGS_ARMS);
            case WEIGHT_LOSS -> Arrays.asList(Day.UPPER_BODY, Day.LOWER_BODY);
            case STRENGTH -> Arrays.asList(Day.BACK_AND_SHOULDERS, Day.LEGS, Day.CHEST);
            default -> List.of(Day.values());
        };
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
            case LEGS_ARMS -> Set.of("Legs", "Arms");
            case UPPER_BODY -> goal == FitnessGoal.WEIGHT_LOSS ? Set.of("Cardio", "Upper Body") : Set.of("Back", "Chest", "Shoulders", "Arms");
            case LOWER_BODY -> Set.of("Legs", "Cardio");
        };

        return exercises.stream()
                .filter(e -> relevantBodyParts.contains(e.getBodyPart()) || (goal == FitnessGoal.WEIGHT_LOSS && e.getExerciseType() == ExerciseType.CARDIO))
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