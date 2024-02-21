package com.decadev.services;

import com.decadev.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exerciseService;


    @BeforeEach
    void setUp() {
        // Directly call init to populate the exercises for testing
        exerciseService.init();
        System.out.println("Number of exercises: " + exerciseService.getExercises().size());
        // Print out details of each exercise
        exerciseService.getExercises().forEach(System.out::println);
        User user = User.builder()
                .userId("sampleUserId")
                .username("sampleUsername")
                .email("sample@example.com")
                .gymAccess(GymAccess.FULL_GYM_ACCESS)
                .fitnessLevel(FitnessLevel.BEGINNER)
                .fitnessGoal(FitnessGoal.WEIGHT_LOSS)
                .availability(5)
                .build();
        user.setPassword("samplePassword");
    }


    static Stream<Arguments> fitnessGoalAndLevelProvider() {
        return Stream.of(
                // Weight Loss Goals
                Arguments.of(FitnessLevel.BEGINNER, FitnessGoal.WEIGHT_LOSS, 12, 15),
                Arguments.of(FitnessLevel.INTERMEDIATE, FitnessGoal.WEIGHT_LOSS, 10, 12),
                Arguments.of(FitnessLevel.ADVANCED, FitnessGoal.WEIGHT_LOSS, 8, 10),
                Arguments.of(FitnessLevel.EXPERT, FitnessGoal.WEIGHT_LOSS, 6, 8),

                // Muscle Building Goals
                Arguments.of(FitnessLevel.BEGINNER, FitnessGoal.BUILD_MUSCLE, 8, 10),
                Arguments.of(FitnessLevel.INTERMEDIATE, FitnessGoal.BUILD_MUSCLE, 6, 8),
                Arguments.of(FitnessLevel.ADVANCED, FitnessGoal.BUILD_MUSCLE, 4, 6),
                Arguments.of(FitnessLevel.EXPERT, FitnessGoal.BUILD_MUSCLE, 3, 4),

                // Strength Goals
                Arguments.of(FitnessLevel.BEGINNER, FitnessGoal.STRENGTH, 6, 8),
                Arguments.of(FitnessLevel.INTERMEDIATE, FitnessGoal.STRENGTH, 4, 6),
                Arguments.of(FitnessLevel.ADVANCED, FitnessGoal.STRENGTH, 3, 4),
                Arguments.of(FitnessLevel.EXPERT, FitnessGoal.STRENGTH, 2, 3)
        );
    }

    @Test
    void whenFilteringByBodyPart_thenCorrectExercisesAreReturned() {
        User user = new User();
        user.setGymAccess(GymAccess.FULL_GYM_ACCESS);
        String bodyPart = "Legs";
        List<Exercise> filteredExercises = exerciseService.filterExercisesByBodyPart(bodyPart, user);
        assertThat(filteredExercises)
                .extracting(Exercise::getBodyPart)
                .containsOnly(bodyPart);
        System.out.println(filteredExercises);
    }

    @ParameterizedTest
    @EnumSource(FitnessGoal.class)
    void whenFilteringByGoal_thenCorrectTypeOfExercisesAreReturned(FitnessGoal goal) {
        List<Exercise> exercises = exerciseService.getCustomizedExercises(new User());
        List<Exercise> filteredExercises = exerciseService.filterExercisesByGoal(exercises, goal);
        System.out.println("Filtered exercises for goal " + goal + ": " + filteredExercises);
        System.out.println("Exercise types for goal " + goal + ":");
        filteredExercises.forEach(exercise -> System.out.println(exercise.getName() + ": " + exercise.getExerciseType()));
        assertThat(filteredExercises)
                .as("Checking exercises filtered by goal: %s", goal)
                .isNotEmpty()
                .allSatisfy(exercise -> {
                    if (goal == FitnessGoal.WEIGHT_LOSS) {
                        assertThat(exercise.getExerciseType()).isEqualTo(ExerciseType.CARDIO);
                    } else if (goal == FitnessGoal.BUILD_MUSCLE) {
                        assertThat(exercise.getExerciseType()).isEqualTo(ExerciseType.COMPOUND);
                    } else if (goal == FitnessGoal.STRENGTH) {
                        assertThat(exercise.getExerciseType()).isIn(ExerciseType.COMPOUND, ExerciseType.ISOLATION);
                    }
                });
    }

    @Test
    void whenCustomizingExerciseIntensityForWeightLoss_thenRepsAreAdjustedAccordingly() {
        Exercise exercise = new Exercise("Test", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Legs", "None", 3, 10, Duration.ofMinutes(10));
        Exercise customizedExercise = exerciseService.customizeExerciseIntensity(exercise, FitnessLevel.BEGINNER, FitnessGoal.WEIGHT_LOSS);
        assertTrue(customizedExercise.getReps() >= 10 && customizedExercise.getReps() <= 15);
    }

    @Test
    void whenCalculatingSessionsPerWeek_withAvailability_thenReturnsCorrectSessionCount() {
        int sessions = exerciseService.calculateSessionsPerWeek(3, FitnessGoal.BUILD_MUSCLE);
        // Assuming logic based on availability and goal
        assertEquals(2, sessions);
    }

    @Test
    void whenFilteringByBodyPartForStrength_thenOnlyCompoundExercisesAreReturned() {
        User user = new User();
        user.setGymAccess(GymAccess.FULL_GYM_ACCESS);
        List<Exercise> exercises = exerciseService.filterExercisesByBodyPartForStrength("Legs", user);
        assertTrue(exercises.stream().allMatch(exercise -> exercise.getExerciseType() == ExerciseType.COMPOUND));
    }

    @Test
    void whenExerciseListIsNull_thenFilterByBodyPartReturnsEmptyList() {
        exerciseService.setExercises(null);
        User user = new User();
        user.setGymAccess(GymAccess.FULL_GYM_ACCESS);
        List<Exercise> filteredExercises = exerciseService.filterExercisesByBodyPart("Legs", user);
        assertThat(filteredExercises).isEmpty();
    }

    @Test
    void whenExerciseListIsEmpty_thenFilterByBodyPartReturnsEmptyList() {
        exerciseService.setExercises(Collections.emptyList());
        User user = new User();
        user.setGymAccess(GymAccess.FULL_GYM_ACCESS);
        List<Exercise> filteredExercises = exerciseService.filterExercisesByBodyPart("Legs", user);
        assertThat(filteredExercises).isEmpty();
    }

    @Test
    void whenNoExercisesMatchFilterCriteria_thenReturnsEmptyList() {
        User user = new User();
        user.setGymAccess(GymAccess.FULL_GYM_ACCESS);
        exerciseService.setExercises(Collections.emptyList());
        List<Exercise> filteredExercises = exerciseService.filterExercisesByBodyPart("Wings", user);
        assertThat(filteredExercises).isEmpty();
    }

    @Test
    void whenFilteringByGymAccess_thenCorrectExercisesAreReturned() {
        ExerciseService exerciseService = new ExerciseService();
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Exercise 1", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Legs",
                "Dumbbells", 3, 10, Duration.ofMinutes(30)));
        exercises.add(new Exercise("Exercise 2", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs",
                "Barbell", 3, 12, Duration.ofMinutes(30)));
        exercises.add(new Exercise("Exercise 3", FitnessLevel.ADVANCED, ExerciseType.ISOLATION, "Core",
                "Foam Roller", 3, 15, Duration.ofMinutes(30)));
        exerciseService.setExercises(exercises);

        List<Exercise> filteredExercises = exerciseService.filterExercisesByGymAccess(GymAccess.FULL_GYM_ACCESS);

        assertFalse(filteredExercises.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("fitnessGoalAndLevelProvider")
    void testCustomizeExerciseIntensity(FitnessLevel fitnessLevel, FitnessGoal fitnessGoal, int expectedMinReps, int expectedMaxReps) {
        Exercise testExercise = new Exercise("Test Exercise", fitnessLevel, ExerciseType.COMPOUND, "Legs", "None", 3, 10, Duration.ofMinutes(10));
        Exercise customizedExercise = exerciseService.customizeExerciseIntensity(testExercise, fitnessLevel, fitnessGoal);
        assertThat(customizedExercise.getReps()).isBetween(expectedMinReps, expectedMaxReps);
    }

    @Test
    void calculateSessionsPerWeekForVariousGoals() {
        assertThat(exerciseService.calculateSessionsPerWeek(4, FitnessGoal.BUILD_MUSCLE)).isEqualTo(2);
        assertThat(exerciseService.calculateSessionsPerWeek(5, FitnessGoal.WEIGHT_LOSS)).isEqualTo(4);
        assertThat(exerciseService.calculateSessionsPerWeek(6, FitnessGoal.STRENGTH)).isEqualTo(3);
    }

    @Test
    void calculateSessionsPerWeekForWeightLoss() {
        int sessions = exerciseService.calculateSessionsPerWeek(5, FitnessGoal.WEIGHT_LOSS);
        assertEquals(4, sessions);
    }

}
