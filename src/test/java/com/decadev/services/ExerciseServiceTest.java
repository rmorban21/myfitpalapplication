package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.enums.ExerciseType;
import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import com.decadev.enums.GymAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize your service with some mock data if necessary
        exerciseService.init();
    }

    @Test
    @DisplayName("Test Equipment Accessibility")
    void testIsEquipmentAccessible() {
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.FULL_GYM_ACCESS, "Barbell"));
        assertFalse(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_NO_WEIGHTS, "Dumbbells"));
        // Add more assertions as needed
    }

    @Test
    @DisplayName("Test Exercise Customization by Intensity")
    void testCustomizeExerciseIntensity() {
        Exercise exercise = new Exercise();
        exercise = exerciseService.customizeExerciseIntensity(exercise, FitnessLevel.BEGINNER, FitnessGoal.BUILD_MUSCLE);
        assertNotNull(exercise.getReps());
        // Add more assertions for different fitness levels and goals
    }

    @Test
    @DisplayName("Test Getting Customized Exercises for User")
    void testGetCustomizedExercisesForUser() {
        List<Exercise> exercises = exerciseService.getCustomizedExercisesForUser(GymAccess.HOME_GYM_WITH_WEIGHTS, FitnessLevel.BEGINNER, FitnessGoal.BUILD_MUSCLE);
        assertFalse(exercises.isEmpty());

        // Printing out the exercises for debugging
        System.out.println("Customized Exercises for User:");
        exercises.forEach(exercise -> {
            System.out.println("Name: " + exercise.getName() + ", Type: " + exercise.getExerciseType()
                    + ", Body Part: " + exercise.getBodyPart() + ", Equipment: " + exercise.getEquipment()
                    + ", Sets: " + exercise.getSets() + ", Reps: " + (exercise.getReps() != null ? exercise.getReps() : "N/A")
                    + ", Duration: " + (exercise.getDuration() != null ? exercise.getDuration() : "N/A"));
        });

    }

    @Test
    @DisplayName("Test Exercise Suitability for Fitness Level")
    void testIsExerciseSuitableForFitnessLevel() {
        Exercise exercise = new Exercise();
        exercise.setFitnessLevel(FitnessLevel.BEGINNER);
        assertTrue(exerciseService.isExerciseSuitableForFitnessLevel(exercise, FitnessLevel.BEGINNER));
        // Add more cases as needed
    }

    @Test
    @DisplayName("Test Exercise Customization for Goal")
    void testCustomizeExerciseForGoal() {
        Exercise exercise = new Exercise();
        exercise = exerciseService.customizeExerciseForGoal(exercise, FitnessGoal.WEIGHT_LOSS);
        assertNotNull(exercise.getSets());
        // Verify customization logic is applied correctly
    }

    // Add more tests as needed for all methods and edge cases
}