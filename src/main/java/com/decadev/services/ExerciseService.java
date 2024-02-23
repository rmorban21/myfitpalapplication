package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.enums.ExerciseType;
import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private List<Exercise> exercises = new ArrayList<>();
    private List <Exercise> priorityExercises = new ArrayList<>();
    private List <Exercise> accessoryExercises = new ArrayList<>();
    private List <Exercise> coreExercises = new ArrayList<>();
    private List <Exercise> cardioExercises = new ArrayList<>();
    private List <Exercise> strengthExercises = new ArrayList<>();

    /**
     * Initializes the exercise lists.
     * This method would typically populate the various lists (priority, accessory, core, cardio, strength) with exercises.
     * Each exercise would be tailored to fit different fitness goals and levels.
     */
    @PostConstruct
    public void init() {
        // Core Exercises
        coreExercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).duration(Duration.ofSeconds(60)).build());
        coreExercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(15).build());
        coreExercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());
        coreExercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(10).build());
        coreExercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());

        // Cardio Exercises
        cardioExercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("StairMaster").duration(Duration.ofMinutes(15)).build());
        cardioExercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(30)).build());
        cardioExercises.add(Exercise.builder().name("Incline Treadmill Walk/Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(15)).build());

        //Priority Leg Exercises
        priorityExercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Hack Squat Machine").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Barbell").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Press Machine").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Barbell").sets(3).reps(12).build());

        //Accessory Leg Exercises
        accessoryExercises.add(Exercise.builder().name("Leg Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Extension Machine").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Leg Curl").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Curl Machine").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Calf Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Legs").equipment("Calf Raise Machine").sets(3).reps(15).build());
        accessoryExercises.add(Exercise.builder().name("Bulgarian Split Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Smith Machine Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Smith Machine").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Walking Dumbbells Lunges").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Dumbbells").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Walking Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Lateral Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Single-Leg Deadlifts").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Body-weight Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Wall Sits").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").duration(Duration.ofSeconds(60)).build());

        // Priority Back Exercises
        priorityExercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Lat Pulldown Machine").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("T-bar machine").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(3).reps(10).build());
        priorityExercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Dumbbells").sets(3).reps(10).build());

        //Accessory Back Exercises
        accessoryExercises.add(Exercise.builder().name("Pull-up").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Pull-up Bar or Machine").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Cable Face Pulls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Cable Machine").sets(3).reps(15).build());
        accessoryExercises.add(Exercise.builder().name("Single Arm Dumbbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Wide Grip Pull-ups").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Pull-Up Bar or Machine").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Seated Cable Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Seated Cable Row Machine").sets(3).reps(12).build());

        //Priority Chest Exercises
        priorityExercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        priorityExercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        priorityExercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        priorityExercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());

        //Accessory Chest Exercises
        accessoryExercises.add(Exercise.builder().name("Push-up Variations").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("None").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Dumbbell Chest Flyes").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Chest").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Cable Crossovers").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Cable Machine").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Decline Bench Press").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        accessoryExercises.add(Exercise.builder().name("Chest Dips").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Dip Bar").sets(3).reps(12).build());

        //Priority Shoulder Exercises
        priorityExercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Barbell").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(12).build());
        priorityExercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(12).build());

        //Accessory Shoulder Exercises
        accessoryExercises.add(Exercise.builder().name("Dumbbell Lateral Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());
        accessoryExercises.add(Exercise.builder().name("Dumbbell Front Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());
        accessoryExercises.add(Exercise.builder().name("Dumbbell Rear Delt Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());

        //Arm Exercises
        accessoryExercises.add(Exercise.builder().name("Dumbbell Bicep Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Dumbbell Tricep Kickbacks").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("SkullCrusher").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Arms").equipment("Barbell").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Preacher Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Arms").equipment("Barbell").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Tricep Overhead Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Barbell Hammer Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        accessoryExercises.add(Exercise.builder().name("Chair Dips").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("None").sets(3).reps(12).build());

//Strength Exercises with relevant reps and sets for strength goal
        //Legs
        strengthExercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_LEGS).bodyPart("Legs").equipment("Hack Squat Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_LEGS).bodyPart("Legs").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_LEGS).bodyPart("Legs").equipment("Leg Press Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_LEGS).bodyPart("Legs").equipment("Barbell").sets(5).reps(5).build());

        //Push
        strengthExercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Chest").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Chest").equipment("Bench Press").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Chest").equipment("Bench Press").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Chest").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Shoulders").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Shoulders").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PUSH).bodyPart("Shoulders").equipment("Dumbbells").sets(5).reps(5).build());

        //Pull
        strengthExercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("Lat Pulldown Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("T-bar machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Rack Pulls").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.STRENGTH_PULL).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
    }
    //TODO: need to make sure buildmuscle and strength goals excluse cardio exercises
        // TODO: subsequentially need to make sure build muscle includes all priority and accessory exercises relevant to user fitness level
        // TODO: need to make sure strength includes all strengthexercises and accessory exercises relevant to user fitness level
        // TODO: need to  make sure weightloss includes all prioirty exercises and cardio exercises relevant to user fitness level

    /**
     * Generates a list of exercises customized to the user's fitness level and goal.
     * It smartly incorporates core exercises for all users, tailors the exercise selection based on the goal
        * (building muscle, strength, or weight loss), and specifically adds cardio exercises for those with weight loss goals.
     * @param fitnessLevel - user's fitness level.
     * @param fitnessGoal -user's fitness goal.
     * @return - returns a customized list of exercises for the user's fitness level and goal.
     */
    public List<Exercise> getCustomizedExercisesForUser(FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        List<Exercise> customizedExercises = new ArrayList<>(coreExercises); // Core exercises for all users

        // Add goal-specific exercises with appropriate filtering and settings
        switch (fitnessGoal) {
            case BUILD_MUSCLE:
                // Include all priority and accessory exercises, exclude cardio
                customizedExercises.addAll(filterExercisesByLevel(priorityExercises, fitnessLevel));
                customizedExercises.addAll(filterExercisesByLevel(accessoryExercises, fitnessLevel));
                break;
            case STRENGTH:
                // Include all strength (retain 5x5) and accessory exercises, exclude cardio
                customizedExercises.addAll(filterStrengthExercises(strengthExercises, fitnessLevel));
                customizedExercises.addAll(filterExercisesForStrengthAccessory(accessoryExercises, fitnessLevel));
                break;
            case WEIGHT_LOSS:
                // Include priority exercises and all cardio, suitable for fitness level
                customizedExercises.addAll(filterExercisesByLevel(priorityExercises, fitnessLevel));
                customizedExercises.addAll(cardioExercises); // No need to filter by level as cardio is generally applicable
                break;
        }
        return customizedExercises;
    }

    private List<Exercise> filterExercisesByLevel(List<Exercise> exercises, FitnessLevel fitnessLevel) {
        return exercises.stream()
                .filter(e -> isExerciseSuitableForFitnessLevel(e, fitnessLevel))
                .collect(Collectors.toList());
    }

    private List<Exercise> filterStrengthExercises(List<Exercise> exercises, FitnessLevel fitnessLevel) {
        // Filter strength exercises without modifying their predefined sets and reps
        return filterExercisesByLevel(exercises, fitnessLevel);
    }

    private List<Exercise> filterExercisesForStrengthAccessory(List<Exercise> exercises, FitnessLevel fitnessLevel) {
        // Adjust accessory exercises for strength training (if necessary)
        // This example maintains original rep/set but could be modified if needed
        return exercises.stream()
                .filter(e -> isExerciseSuitableForFitnessLevel(e, fitnessLevel))
                .collect(Collectors.toList());
    }

    /**
     * Determines if an exercise is suitable for a user's fitness level, facilitating filtering of exercises to match user capability and ensuring progression.
     * @param exercise - specific exercise from the exercise data.
     * @param fitnessLevel - user's fitness level.
     * @return - true or false based on the relevance of the specific exercises based on fitness level.
     */
    public boolean isExerciseSuitableForFitnessLevel(Exercise exercise, FitnessLevel fitnessLevel) {
        return switch (fitnessLevel) {
            case BEGINNER -> exercise.getFitnessLevel() == FitnessLevel.BEGINNER || exercise.getFitnessLevel() == FitnessLevel.INTERMEDIATE;
            case INTERMEDIATE -> exercise.getFitnessLevel() != FitnessLevel.EXPERT; // Intermediate can do beginner to advanced
            case ADVANCED, EXPERT -> true; // Advanced and expert can do any level
        };
    }

    /**
     * Provides a comprehensive list of all exercises available in the service, potentially useful for debugging, administration, or logging purposes.
     * @return copy of all exercises.
     */
    public List<Exercise> getAllExercises() {
        // Initialize a new list to hold all exercises
        List<Exercise> allExercises = new ArrayList<>();

        // Add all exercises from each specific list into the allExercises list
        allExercises.addAll(priorityExercises);
        allExercises.addAll(accessoryExercises);
        allExercises.addAll(coreExercises);
        allExercises.addAll(cardioExercises);
        allExercises.addAll(strengthExercises);

        // Return the combined list of all exercises
        return allExercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}
