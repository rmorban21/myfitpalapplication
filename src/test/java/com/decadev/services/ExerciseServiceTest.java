package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.enums.ExerciseType;
import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;

public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exerciseService.init(); // Manually call to simulate @PostConstruct
    }

    @Test
    void testGetCustomizedExercisesForUser_BuildMuscle_Advanced() {
        // Test for BUILD_MUSCLE goal with ADVANCED fitness level
        List<Exercise> result = exerciseService.getCustomizedExercisesForUser(FitnessLevel.ADVANCED, FitnessGoal.BUILD_MUSCLE);

        // Check for non-empty result
        assertFalse(result.isEmpty(), "Result should not be empty");

        // Ensure compound exercises are included
        assertTrue(result.stream().anyMatch(e -> e.getExerciseType().equals(ExerciseType.COMPOUND)), "Should include compound exercises");

        // Ensure core exercises are included
        assertTrue(result.stream().anyMatch(e -> e.getBodyPart().equalsIgnoreCase("Core")), "Should include core exercises");

        // Ensure cardio exercises are excluded
        assertFalse(result.stream().anyMatch(e -> e.getExerciseType().equals(ExerciseType.CARDIO)), "Should not include cardio exercises");

    }

    @Test
    void testGetCustomizedExercisesForUser_Strength_Beginner() {
        // Test for STRENGTH goal with BEGINNER fitness level
        List<Exercise> result = exerciseService.getCustomizedExercisesForUser(FitnessLevel.BEGINNER, FitnessGoal.STRENGTH);

        // Check for non-empty result and 5x5 setup in strength exercises
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(result.stream().anyMatch(e -> e.getSets() == 5 && e.getReps() == 5), "Should include 5x5 strength exercises");

        // Ensure cardio exercises are excluded
        assertFalse(result.stream().anyMatch(e -> e.getExerciseType().equals(ExerciseType.CARDIO)), "Should not include cardio exercises");

        result.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetCustomizedExercisesForUser_WeightLoss_Intermediate() {
        // Test for WEIGHT_LOSS goal with INTERMEDIATE fitness level
        List<Exercise> result = exerciseService.getCustomizedExercisesForUser(FitnessLevel.INTERMEDIATE, FitnessGoal.WEIGHT_LOSS);

        // Check for non-empty result and inclusion of cardio exercises
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertTrue(result.stream().anyMatch(e -> e.getExerciseType().equals(ExerciseType.CARDIO)), "Should include cardio exercises");

        // Ensure the correct mix of priority (compound) exercises and cardio
        assertTrue(result.stream().anyMatch(e -> e.getExerciseType().equals(ExerciseType.COMPOUND)), "Should include compound exercises");

        result.forEach(exercise -> System.out.println(exercise));
    }

    // Test to ensure exercises are filtered correctly by fitness level
    @Test
    void testIsExerciseSuitableForFitnessLevel() {
        Exercise beginnerExercise = new Exercise("Test", FitnessLevel.BEGINNER, ExerciseType.ISOLATION, "Arms", "None", 3, 10, Duration.ofSeconds(0));
        assertTrue(exerciseService.isExerciseSuitableForFitnessLevel(beginnerExercise, FitnessLevel.ADVANCED), "A beginner exercise should be suitable for an advanced user");
    }

    // Test to ensure all exercises can be retrieved
    @Test
    void testGetAllExercises() {
        List<Exercise> allExercises = exerciseService.getAllExercises();
        assertNotNull(allExercises, "Exercise list should not be null");
        assertFalse(allExercises.isEmpty(), "Exercise list should be populated");

        allExercises.forEach(exercise -> System.out.println(exercise));
    }

}
