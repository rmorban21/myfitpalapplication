package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.enums.*;
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
    void testGetExercisesForDay_beginnerUserWithBuildMuscleGoalOnLegDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.BUILD_MUSCLE;
        FitnessLevel fitnessLevel = FitnessLevel.BEGINNER;
        Day day = Day.LEGS;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.LEGS), "Exercises should contain LEGS body part.");
        exercises.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetExercisesForDay_advancedUserWithBuildMuscleGoalOnBackAndShouldersDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.BUILD_MUSCLE;
        FitnessLevel fitnessLevel = FitnessLevel.ADVANCED;
        Day day = Day.BACK_AND_SHOULDERS;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.BACK), "Exercises should contain Back body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.SHOULDERS), "Exercises should contain Back body part.");
        exercises.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetExercisesForDay_expertUserWithBuildMuscleGoalOnArmAndCoreDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.BUILD_MUSCLE;
        FitnessLevel fitnessLevel = FitnessLevel.EXPERT;
        Day day = Day.ARMS_CORE;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.ARMS), "Exercises should contain Arms body part.");
        exercises.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetExercisesForDay_beginnerUserWithStrengthGoalOnPushDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.STRENGTH;
        FitnessLevel fitnessLevel = FitnessLevel.BEGINNER;
        Day day = Day.PUSH;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.CHEST), "Exercises should contain Chest body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.SHOULDERS), "Exercises should contain Shoulders body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getExerciseType() == ExerciseType.COMPOUND_STRENGTH), "Exercises should contain Back body part.");
        exercises.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetExercisesForDay_expertUserWithWeightLossGoalOnUpperBodyDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.WEIGHT_LOSS;
        FitnessLevel fitnessLevel = FitnessLevel.EXPERT;
        Day day = Day.UPPER_BODY;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.CHEST), "Exercises should contain Chest body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.SHOULDERS), "Exercises should contain Shoulders body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.BACK), "Exercises should contain Back body part.");

        exercises.forEach(exercise -> System.out.println(exercise));
    }

    @Test
    void testGetExercisesForDay_beginnerUserWithWeightLossGoalOnLowerBodyDay_Success() {
        // Assuming the init method populates exercises suitable for LEGS day for BUILD_MUSCLE goal
        FitnessGoal fitnessGoal = FitnessGoal.WEIGHT_LOSS;
        FitnessLevel fitnessLevel = FitnessLevel.BEGINNER;
        Day day = Day.LOWER_BODY;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertFalse(exercises.isEmpty(), "Exercises list should not be empty for a valid day and goal combination.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.LEGS), "Exercises should contain Chest body part.");
        assertTrue(exercises.stream().anyMatch(e -> e.getBodyPart() == BodyPart.CARDIO), "Exercises should contain Cardio body part.");

        exercises.forEach(exercise -> System.out.println(exercise));
    }


    @Test
    void testGetExercisesForDay_returnsEmptyList() {
        // Assuming the service should not return BACK_AND_SHOULDERS exercises for a WEIGHT_LOSS goal
        FitnessGoal fitnessGoal = FitnessGoal.WEIGHT_LOSS;
        FitnessLevel fitnessLevel = FitnessLevel.BEGINNER;
        Day day = Day.BACK_AND_SHOULDERS;

        List<Exercise> exercises = exerciseService.getExercisesForDay(fitnessLevel, fitnessGoal, day);

        assertTrue(exercises.isEmpty(), "Exercises list should be empty for a mismatched goal and day combination.");
        exercises.forEach(exercise -> System.out.println(exercise));
    }
}