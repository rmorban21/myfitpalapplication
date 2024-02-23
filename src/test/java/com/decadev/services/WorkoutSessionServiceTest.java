package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.entities.User;
import com.decadev.entities.WorkoutSession;
import com.decadev.enums.*;
import com.decadev.repositories.WorkoutSessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WorkoutSessionServiceTest {

    @Mock
    private WorkoutSessionRepository workoutSessionRepository;

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private WorkoutSessionService workoutSessionService;

    public WorkoutSessionServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateWorkoutSessions() {
        // Sample user data
        User user = new User();
        user.setUserId("user1");
        user.setAvailability(5); // 5 hours of availability
        user.setFitnessGoal(FitnessGoal.BUILD_MUSCLE);

        // Sample exercise data
        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise = Exercise.builder()
                .name("Exercise 1")
                .fitnessLevel(FitnessLevel.BEGINNER)
                .exerciseType(ExerciseType.COMPOUND)
                .bodyPart("Chest")
                .equipment("None")
                .sets(3)
                .reps(10)
                .duration(Duration.ofMinutes(30))
                .build();
        exercises.add(exercise);

        // Mock exerciseService to return exercises
        when(exerciseService.getCustomizedExercisesForUser(GymAccess.HOME_GYM_WITH_WEIGHTS, FitnessLevel.BEGINNER, FitnessGoal.BUILD_MUSCLE)).thenReturn(exercises);

        // Calculate the expected number of workout sessions based on user's availability
        int expectedSessionCount = user.getAvailability();

        // Sample workout session data
        WorkoutSession session = new WorkoutSession();
        session.setUserId("user1");
        session.setExercises(exercises);
        session.setSessionDuration(Duration.ofHours(1));

        // Mock workoutSessionRepository to return saved session
        when(workoutSessionRepository.save(any(WorkoutSession.class))).thenReturn(session);

        // Generate workout sessions
        List<WorkoutSession> actualSessions = workoutSessionService.generateWorkoutSessions(user);

        // Assertions
        assertEquals(expectedSessionCount, actualSessions.size());

        // Verify each generated session
        for (WorkoutSession actualSession : actualSessions) {
            assertEquals(session.getUserId(), actualSession.getUserId());
            assertEquals(session.getExercises().size(), actualSession.getExercises().size());
            assertEquals(session.getSessionDuration(), actualSession.getSessionDuration());

            // Print out the details of each workout session
            System.out.println("Workout Session:");
            System.out.println("User ID: " + actualSession.getUserId());
            System.out.println("Exercises: " + actualSession.getExercises());
            System.out.println("Session Duration: " + actualSession.getSessionDuration());
            System.out.println("----------------------------------");
        }
    }



}