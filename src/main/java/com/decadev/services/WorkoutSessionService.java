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

public class WorkoutSessionService {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;


    // Constructor
    public WorkoutSessionService(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public List<WorkoutSession> generateWorkoutSessionsForUser(User user) {
        // Determine number of sessions from user's availability
        int sessionsPerWeek = user.getAvailability(); // Assuming 1 session = 1 hour

        // Map days to exercises based on user's fitness goal and level
        Map<Day, List<Exercise>> exercisesForDays = mapExercisesToDays(user);

        // Create and save sessions
        List<WorkoutSession> sessions = new ArrayList<>();
        for (Map.Entry<Day, List<Exercise>> entry : exercisesForDays.entrySet()) {
            WorkoutSession session = new WorkoutSession();
            session.setUserId(user.getUserId());
            session.setDay(entry.getKey());
            session.setExercises(entry.getValue());
            // Assuming each session is 1 hour for simplicity
            session.setSessionDuration(Duration.ofHours(1));

            workoutSessionRepository.save(session); // Save session to DynamoDB
            sessions.add(session);
        }

        return sessions;
    }

    private int calculateSessionsBasedOnAvailability(int availabilityHoursPerWeek) {
        // Assuming 1 hour per session, this is straightforward. Adjust logic if sessions vary in length.
        return availabilityHoursPerWeek;
    }

    private List<Day> getWorkoutDaysForGoal(FitnessGoal fitnessGoal) {
        // Return a list of Day enums based on the fitness goal. This requires a mapping from goals to Day enums.
        // This mapping could be hardcoded or configured externally.
        return null;
    }

    private Map<Day, List<Exercise>> mapExercisesToDays(User user) {
        // Logic to map exercises to days based on user's goal and level
        // This involves filtering and selecting exercises from ExerciseService
        // and organizing them by Day, taking into account the target body parts
        return null;
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