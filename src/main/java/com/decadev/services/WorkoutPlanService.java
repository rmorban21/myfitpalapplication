package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.repositories.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService {
    @Autowired
    private  ExerciseService  exerciseService;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private WorkoutSessionService workoutSessionService;

    //TODO: need to revisit how usr aavailabiltiy is retrieved- workout sessions are not properly distributed based on availability
    private String GENERAL_ADVICE = "Remember to stretch before and after your workout for at least 5 minutes. " +
            "Dynamic stretches are recommended before starting to prepare your muscles. " +
            "After your workout, use static stretches to aid in recovery. " +
            "Incorporate full-body foam rolling (SMR) to help release muscle tightness and improve flexibility. " +
            "Visit our website for detailed guides and tutorials on effective stretching and SMR techniques.";


    //TODO: Consider exceptions to add - possible improvements to personalization

    /**
     * Generates and saves a new workout plan for a given user. If an existing plan is found, it is replaced.
     *
     * @param user The user for whom the workout plan is being generated.
     * @return The saved workout plan.
     */
    public WorkoutPlan generateAndSaveWorkoutPlan(User user) {
        // Check if user already has a plan and delete it
        workoutPlanRepository.deleteByUserId(user.getUserId());

        // Generate new workout sessions
        List<WorkoutSession> sessions = workoutSessionService.generateWorkoutSessions(user);
        List<String> sessionIds = sessions.stream()
                .map(WorkoutSession::getSessionId)
                .collect(Collectors.toList());

        // Create and populate a new workout plan
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setUserId(user.getUserId());
        workoutPlan.setWorkoutSessionIds(sessionIds);
        workoutPlan.setFitnessGoal(user.getFitnessGoal());
        workoutPlan.setFitnessLevel(user.getFitnessLevel());
        workoutPlan.setAvailability(user.getAvailability());
        workoutPlan.setGeneralAdvice(GENERAL_ADVICE);

        // Save the new plan
        return workoutPlanRepository.save(workoutPlan);
    }

    /**
     * Retrieves all workout sessions associated with the user's workout plan.
     *
     * @param userId The unique identifier of the user.
     * @return A list of workout sessions associated with the user's workout plan.
     * @throws IllegalArgumentException if the workout plan or any sessions are not found.
     */
    public List<WorkoutSession> getWorkoutSessionsForUser(String userId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findWorkoutPlanByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Workout plan not found for user ID: " + userId));

        return workoutPlan.getWorkoutSessionIds().stream()
                .map(sessionId -> workoutSessionService.findWorkoutSessionById(sessionId)
                        .orElseThrow(() -> new IllegalArgumentException("Workout session not found with ID: " + sessionId)))
                .collect(Collectors.toList());
    }

    /**
     * Finds a workout plan by the user's ID.
     *
     * @param userId The unique identifier of the user.
     * @return An Optional containing the workout plan if found, or an empty Optional if not found.
     */
    public Optional<WorkoutPlan> findWorkoutPlanByUserId(String userId) {
        return workoutPlanRepository.findWorkoutPlanByUserId(userId);
    }


}
