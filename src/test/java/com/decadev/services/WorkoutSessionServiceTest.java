//package com.decadev.services;
//
//import com.decadev.entities.Exercise;
//import com.decadev.entities.User;
//import com.decadev.entities.WorkoutSession;
//import com.decadev.enums.*;
//import com.decadev.repositories.WorkoutSessionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.Duration;
//import java.util.*;
//
//class WorkoutSessionServiceTest {
//
//    @Mock
//    private WorkoutSessionRepository workoutSessionRepository;
//
//    @Mock
//    private ExerciseService exerciseService;
//
//    @InjectMocks
//    private WorkoutSessionService workoutSessionService;
//
//
//    private List<Exercise> exercises;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        exercises = initExercises();
//        // Assume the ExerciseService.getCustomizedExercisesForUser method will return this list for tests
//        when(exerciseService.getCustomizedExercisesForUser(any(FitnessLevel.class), any(FitnessGoal.class))).thenReturn(exercises);
//    }
//
//    private List<Exercise> initExercises() {
//        List<Exercise> exercises = new ArrayList<>();
//
//        exercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).duration(Duration.ofSeconds(60)).build());
//        exercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(15).build());
//        exercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());
//
//        // Cardio Exercises
//        exercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("StairMaster").duration(Duration.ofMinutes(15)).build());
//        exercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration(Duration.ofMinutes(30)).build());
//        exercises.add(Exercise.builder().name("Incline Treadmill Walk/Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration(Duration.ofMinutes(15)).build());
//
//        //Priority Leg Exercises
//        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Hack Squat Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Leg Press Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Walking Dumbbells Lunges").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Dumbbells").sets(3).reps(10).build());
//        //Accessory Leg Exercises
//        exercises.add(Exercise.builder().name("Leg Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Extension Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Leg Curl").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Curl Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Calf Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Calf Raise Machine").sets(3).reps(15).build());
//        exercises.add(Exercise.builder().name("Bulgarian Split Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Walking Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Lateral Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Single-Leg Deadlifts").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Body-weight Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Wall Sits").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").duration(Duration.ofSeconds(60)).build());
//
//        // Priority Back Exercises
//        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Lat Pulldown Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("T-bar machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(10).build());
//
//        //Accessory Back Exercises
//        exercises.add(Exercise.builder().name("Pull-up").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-up Bar or Machine").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Cable Face Pulls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Cable Machine").sets(3).reps(15).build());
//        exercises.add(Exercise.builder().name("Single Arm Dumbbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Wide Grip Pull-ups").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-Up Bar or Machine").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Seated Cable Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Seated Cable Row Machine").sets(3).reps(12).build());
//
//        //Priority Chest Exercises
//        exercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
//
//        //Accessory Chest Exercises
//        exercises.add(Exercise.builder().name("Push-up Variations").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("None").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Dumbbell Chest Flyes").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Cable Crossovers").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Cable Machine").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Decline Bench Press").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
//        exercises.add(Exercise.builder().name("Chest Dips").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dip Bar").sets(3).reps(12).build());
//
//        //Priority Shoulder Exercises
//        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());
//
//        //Accessory Shoulder Exercises
//        exercises.add(Exercise.builder().name("Dumbbell Lateral Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());
//        exercises.add(Exercise.builder().name("Dumbbell Front Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());
//        exercises.add(Exercise.builder().name("Dumbbell Rear Delt Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(15).build());
//
//        //Arm Exercises
//        exercises.add(Exercise.builder().name("Dumbbell Bicep Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Dumbbell Tricep Kickbacks").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("SkullCrusher").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Preacher Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Barbell").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Tricep Overhead Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Barbell Hammer Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("Dumbbells").sets(3).reps(12).build());
//        exercises.add(Exercise.builder().name("Chair Dips").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.ARMS).equipment("None").sets(3).reps(12).build());
//
////Strength Exercises with relevant reps and sets for strength goal
//        //Legs
//        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Hack Squat Machine").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Leg Press Machine").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(5).reps(5).build());
//
//        //Push
//        exercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Barbell").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(5).reps(5).build());
//
//        //Pull
//        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Lat Pulldown Machine").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("T-bar machine").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(5).reps(5).build());
//        exercises.add(Exercise.builder().name("Rack Pulls").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND_STRENGTH).bodyPart(BodyPart.BACK).equipment("Barbell").sets(5).reps(5).build());
//        return exercises;
//    }
//
//    @Test
//    void testGenerateWorkoutSessionsForBeginnerBuildMuscle5Hours() {
//        // Build a user with specified attributes
//        User user = User.builder()
//                .userId("testUser")
//                .username("Test User")
//                .fitnessGoal(FitnessGoal.BUILD_MUSCLE)
//                .fitnessLevel(FitnessLevel.BEGINNER)
//                .availability(5)
//                .build();
//
//        // Generate workout sessions for the user
//        List<WorkoutSession> workoutSessions = workoutSessionService.generateWorkoutSessionsForUser(user);
//
//        // Check the number of workout sessions matches the user's availability
//        assertEquals(5, workoutSessions.size(), "Number of workout sessions should match user's availability.");
//        System.out.println("Number of sessions: " + workoutSessions.size());
//
//        // Variables to track the counts of priority and accessory exercises
//        int totalPriorityExercises = 0;
//        int totalAccessoryExercises = 0;
//
//        // Iterate through each session to count the types of exercises
//        for (WorkoutSession session : workoutSessions) {
//            int priorityCount = 0;
//            int accessoryCount = 0;
//            for (Exercise exercise : session.getExercises()) {
//                if (exercise.getExerciseType() == ExerciseType.PRIORITY) {
//                    priorityCount++;
//                } else if (exercise.getExerciseType() == ExerciseType.ACCESSORY) {
//                    accessoryCount++;
//                }
//            }
//            totalPriorityExercises += priorityCount;
//            totalAccessoryExercises += accessoryCount;
//
//            System.out.println("Session Day: " + session.getDay());
//            System.out.println("Priority Exercises: " + priorityCount);
//            System.out.println("Accessory Exercises: " + accessoryCount);
//            System.out.println("Total Exercises for Day: " + session.getExercises().size());
//        }
//
//        assertTrue(totalPriorityExercises == 5, "Total priority exercises should meet minimum expected count.");
//        assertTrue(totalAccessoryExercises == 5, "Total accessory exercises should meet minimum expected count.");
//
//        // Print total counts for debugging
//        System.out.println("Total Priority Exercises: " + totalPriorityExercises);
//        System.out.println("Total Accessory Exercises: " + totalAccessoryExercises);
//        workoutSessions.forEach(workoutSession -> System.out.println(workoutSession));
//    }
//
//    @Test
//    void testGenerateWorkoutSessionsForUser() {
//        User user = User.builder()
//                .userId("1")
//                .username("testUser")
//                .fitnessGoal(FitnessGoal.BUILD_MUSCLE)
//                .fitnessLevel(FitnessLevel.BEGINNER)
//                .availability(5)
//                .build();
//        when(exerciseService.getCustomizedExercisesForUser(any(), any())).thenReturn(initExercises());
//
//        List<WorkoutSession> sessions = workoutSessionService.generateWorkoutSessionsForUser(user);
//        assertNotNull(sessions);
//        System.out.println(sessions);
//        assertFalse(sessions.isEmpty());
//        System.out.println("Generated Sessions for User: " + sessions.size());
//    }
//
//    @Test
//    void testMapSessionsToDays() {
//        // Directly invoke with a sample list of exercises, goal, and level
//        Map<Day, List<Exercise>> dayToExercisesMap = workoutSessionService.mapSessionsToDays(initExercises(), FitnessGoal.BUILD_MUSCLE, FitnessLevel.BEGINNER, new LinkedList<>(), new LinkedList<>());
//
//        assertNotNull(dayToExercisesMap);
//        assertFalse(dayToExercisesMap.isEmpty());
//        dayToExercisesMap.forEach((day, exercises) -> System.out.println(day + ": " + exercises.size() + " exercises"));
//    }
//    @Test
//    void testSelectExercisesForDay() {
//        Set<BodyPart> targetBodyParts = new HashSet<>(Arrays.asList(BodyPart.LEGS, BodyPart.BACK));
//        List<Exercise> selectedExercises = workoutSessionService.selectExercisesForDay(initExercises(), ExerciseType.PRIORITY, 3, FitnessLevel.BEGINNER, targetBodyParts);
//
//        assertNotNull(selectedExercises);
//        System.out.println("Selected Exercises for Day: " + selectedExercises.size());
//        selectedExercises.forEach(exercise -> System.out.println(exercise.getName()));
//    }
//
//    @Test
//    void testBuildWorkoutSessions() {
//        User user = User.builder()
//                .userId("1")
//                .username("testUser")
//                .fitnessGoal(FitnessGoal.BUILD_MUSCLE)
//                .fitnessLevel(FitnessLevel.BEGINNER)
//                .availability(5)
//                .build();
//        Map<Day, List<Exercise>> dayToExercisesMap = workoutSessionService.mapSessionsToDays(initExercises(), FitnessGoal.BUILD_MUSCLE, FitnessLevel.BEGINNER, new LinkedList<>(), new LinkedList<>());
//
//        List<WorkoutSession> sessions = workoutSessionService.buildWorkoutSessions(dayToExercisesMap, user);
//        assertNotNull(sessions);
//        assertEquals(5, sessions.size());
//        sessions.forEach(session -> System.out.println("Session Day: " + session.getDay() + ", Exercises: " + session.getExercises().size()));
//    }
//
//}