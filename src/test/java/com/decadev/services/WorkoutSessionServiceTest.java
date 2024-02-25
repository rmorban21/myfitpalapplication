package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.entities.User;
import com.decadev.entities.WorkoutSession;
import com.decadev.enums.BodyPart;
import com.decadev.enums.ExerciseType;
import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import com.decadev.repositories.WorkoutSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class WorkoutSessionServiceTest {

    @Mock
    private WorkoutSessionRepository workoutSessionRepository;

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private WorkoutSessionService workoutSessionService;


    private List<Exercise> exercises;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exercises = initExercises();
        // Assume the ExerciseService.getCustomizedExercisesForUser method will return this list for tests
        when(exerciseService.getCustomizedExercisesForUser(any(FitnessLevel.class), any(FitnessGoal.class))).thenReturn(exercises);
    }

    private List<Exercise> initExercises() {
        List<Exercise> exercises = new ArrayList<>();

        exercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).duration(Duration.ofSeconds(60)).build());
        exercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());

        // Cardio Exercises
        exercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("StairMaster").duration(Duration.ofMinutes(15)).build());
        exercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration(Duration.ofMinutes(30)).build());
        exercises.add(Exercise.builder().name("Incline Treadmill Walk/Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration(Duration.ofMinutes(15)).build());

        //Priority Leg Exercises
        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Hack Squat Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Leg Press Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Walking Dumbbells Lunges").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Dumbbells").sets(3).reps(10).build());
        //Accessory Leg Exercises
        exercises.add(Exercise.builder().name("Leg Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Extension Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Curl").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Curl Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Calf Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Calf Raise Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Bulgarian Split Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Walking Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Lateral Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Single-Leg Deadlifts").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Body-weight Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Wall Sits").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").duration(Duration.ofSeconds(60)).build());

        // Priority Back Exercises
        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Lat Pulldown Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("T-bar machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(10).build());

        //Accessory Back Exercises
        exercises.add(Exercise.builder().name("Pull-up").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Cable Face Pulls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Cable Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Single Arm Dumbbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Wide Grip Pull-ups").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-Up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Seated Cable Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Seated Cable Row Machine").sets(3).reps(12).build());

        //Priority Chest Exercises
        exercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());

        //Accessory Chest Exercises
        exercises.add(Exercise.builder().name("Push-up Variations").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Chest Flyes").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Cable Crossovers").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Cable Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Decline Bench Press").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Chest Dips").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dip Bar").sets(3).reps(12).build());

        //Priority Shoulder Exercises
        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());

        //Accessory Shoulder Exercises
        exercises.add(Exercise.builder().name("Dumbbell Lateral Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Dumbbell Front Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Dumbbell Rear Delt Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());

        //Arm Exercises
        exercises.add(Exercise.builder().name("Dumbbell Bicep Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Tricep Kickbacks").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("SkullCrusher").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Preacher Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Tricep Overhead Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Barbell Hammer Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Chair Dips").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("None").sets(3).reps(12).build());

//Strength Exercises with relevant reps and sets for strength goal
        //Legs
        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Hack Squat Machine").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Leg Press Machine").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(5).reps(5).build());

        //Push
        exercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Barbell").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(5).reps(5).build());

        //Pull
        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Lat Pulldown Machine").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("T-bar machine").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(5).reps(5).build());
        exercises.add(Exercise.builder().name("Rack Pulls").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
        return exercises;
    }

    @Test
    void testBeginnerUserBuildMuscle3Hours() {
        // Given
        User user = User.builder()
                .userId("1")
                .username("testUser")
                .fitnessGoal(FitnessGoal.BUILD_MUSCLE)
                .fitnessLevel(FitnessLevel.BEGINNER)
                .availability(5)
                .build();

        // When
        List<WorkoutSession> sessions = workoutSessionService.generateWorkoutSessionsForUser(user);
        System.out.println(sessions.size());
        // Then
        assertEquals(5, sessions.size(), "There should be 3 sessions generated.");

  //      sessions.forEach(session -> {
  //          assertEquals(Duration.ofHours(1), session.getSessionDuration(), "Each session should last 1 hour.");
  //          assertEquals(4, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.ACCESSORY).count(), "Each session should include 4 accessory exercises.");
  //      });
//
  //      // Verify at least 3 priority exercises for beginners in each session
  //      sessions.forEach(session -> {
  //          assertEquals(3, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.PRIORITY).count(),"Each session should include at least 3 priority exercises for a beginner.");
  //      });
//
  //      sessions.forEach(session -> {
  //          assertEquals(1, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.CORE).count(), "Each session should include 1 core exercises.");
  //      });


        sessions.forEach(workoutSession -> System.out.println(workoutSession));
    }
    @Test
    void testIntermediateUserStrength5Hours() {
        // Given
        User user = User.builder()
                .userId("1")
                .username("testUser")
                .fitnessGoal(FitnessGoal.STRENGTH)
                .fitnessLevel(FitnessLevel.INTERMEDIATE)
                .availability(5)
                .build();

        // When
        List<WorkoutSession> sessions = workoutSessionService.generateWorkoutSessionsForUser(user);

        // Then
        assertEquals(5, sessions.size(), "There should be 5 sessions generated.");

        sessions.forEach(session -> {
            assertEquals(Duration.ofHours(1), session.getSessionDuration(), "Each session should last 1 hour.");
            assertEquals(4, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.ACCESSORY).count(), "Each session should include 4 accessory exercises.");
        });

        // Verify at least 3 priority exercises for beginners in each session
        sessions.forEach(session -> {
            assertEquals(4, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.COMPOUND_STRENGTH).count(),"Each session should include at least 4 priority exercises for a beginner.");
        });

        sessions.forEach(session -> {
            assertEquals(1, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.CORE).count(), "Each session should include 1 core exercises.");
        });

        sessions.forEach(workoutSession -> System.out.println(workoutSession));

        }
    @Test
    void testAdvancedUserWeightLoss4Hours() {
        // Given
        User user = User.builder()
                .userId("1")
                .username("testUser")
                .fitnessGoal(FitnessGoal.WEIGHT_LOSS)
                .fitnessLevel(FitnessLevel.ADVANCED)
                .availability(4)
                .build();

        // When
        List<WorkoutSession> sessions = workoutSessionService.generateWorkoutSessionsForUser(user);

        // Then
        assertEquals(4, sessions.size(), "There should be 4 sessions generated.");

        sessions.forEach(session -> {
            assertEquals(Duration.ofHours(1), session.getSessionDuration(), "Each session should last 1 hour.");
        });

        // Verify at least 3 priority exercises for beginners in each session
        sessions.forEach(session -> {
            assertEquals(5, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.PRIORITY).count(),"Each session should include at least 5 priority exercises for a beginner.");
        });

        sessions.forEach(session -> {
            assertEquals(2, session.getExercises().stream().filter(e -> e.getExerciseType() == ExerciseType.CORE).count(), "Each session should include 2 core exercises.");
        });

        sessions.forEach(workoutSession -> System.out.println(workoutSession));


    }
  // @Test
  // void testExpertUserBuildMuscle6Hours() {
  // }
}