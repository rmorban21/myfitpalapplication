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
    //TODO: populate lists below with exercises for improved exercises
    private List <Exercise> priorityExercises = new ArrayList<>();
    private List <Exercise> accessoryExercises = new ArrayList<>();
    private List <Exercise> coreExercises = new ArrayList<>();
    private List <Exercise> cardioExercises = new ArrayList<>();

    //TODO: populate lists below with exercises with appropriate sets and reps for strength

    //TODO: implement logic to prioritize  priorityexercise list
            // TODO: set reps for all lists

    private List <Exercise> strengthExercises = new ArrayList<>();

    //TODO: possibility for additional exercises for broader range
    //TODO: Review of integration with WorkoutPlan and WorkoutSession via API endpoints that fetch exercises
    // based on specific criteria

    //TODO:organize priority exercises and then populate strength
    @PostConstruct
    public void init() {
        // Core Exercises
        coreExercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).duration(Duration.ofSeconds(60)).build());
        coreExercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(15).build());
        coreExercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());
        coreExercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(10).build());
        coreExercises.add(Exercise.builder().name("Flutter Kicks").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(20).build());
        coreExercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());

        // Cardio Exercises
        cardioExercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("StairMaster").duration(Duration.ofMinutes(15)).build());
        cardioExercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(30)).build());
        cardioExercises.add(Exercise.builder().name("Incline Treadmill Walk").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(15)).build());
        cardioExercises.add(Exercise.builder().name("Jump Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("None").sets(3).reps(15).build());
        cardioExercises.add(Exercise.builder().name("Burpees").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(10).build());
        cardioExercises.add(Exercise.builder().name("Mountain Climbers").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(20).build());
        cardioExercises.add(Exercise.builder().name("High Knees").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("None").sets(3).reps(30).build());
        cardioExercises.add(Exercise.builder().name("Jumping Jacks").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(30).build());

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
        strengthExercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Hack Squat Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Press Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Barbell").sets(5).reps(5).build());

        //Push
        strengthExercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(5).reps(5).build());

        //Pull
        strengthExercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Lat Pulldown Machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("T-bar machine").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Dumbbells").sets(5).reps(5).build());
        strengthExercises.add(Exercise.builder().name("Rack Pulls").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(5).reps(5).build());
    }

    //  implement logic for RPE based on fitness level -
    //TODO: Refactor methods below to use specializedlists including logic for prioritizing exercises and adding  accessories
    //TODO: Ensure logic for beginner and intermediate is correct for determining overall intensity
    // (ie: maxSets for workoutsession (might be implemented in workoutSessionService))
    // TODO: first 2-3 exercises are less  reps  and heavier weight (more intense) reps go up to maximum after third exercise
    // TODO: Need to implement logic for determining first 3 primary exercises
    public Exercise customizeExerciseIntensity(Exercise exercise, FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        switch (fitnessGoal) {
            case BUILD_MUSCLE -> {
                exercise.setSets(3);
                exercise.setReps(fitnessLevel == FitnessLevel.BEGINNER ? 8 : 12);
            }
            case WEIGHT_LOSS -> {
                exercise.setSets(3);
                exercise.setReps(fitnessLevel == FitnessLevel.BEGINNER ? 10 : 15);
                if (exercise.getExerciseType() == ExerciseType.CARDIO) {
                    exercise.setDuration(Duration.ofMinutes(fitnessLevel == FitnessLevel.BEGINNER ? 20 : 30));
                }
            }
            case STRENGTH -> {
                exercise.setSets(fitnessLevel == FitnessLevel.BEGINNER ? 3 : 5);
                exercise.setReps(getRepsForStrengthGoal(fitnessLevel));
            }
        }
        return exercise;
    }


    private int getRepsForStrengthGoal(FitnessLevel fitnessLevel) {
        return switch (fitnessLevel) {
            case BEGINNER, INTERMEDIATE -> 5;
            case ADVANCED, EXPERT -> 3;
        };
    }

    public List<Exercise> getCustomizedExercisesForUser(FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        return exercises.stream()
                .filter(exercise -> isExerciseSuitableForFitnessLevel(exercise, fitnessLevel))
                // Apply goal-specific filters for exercise types
                .filter(exercise -> {
                    switch (fitnessGoal) {
                        case BUILD_MUSCLE:
                            // For BUILD_MUSCLE, exclude cardio exercises
                            return exercise.getExerciseType() != ExerciseType.CARDIO;
                        case STRENGTH:
                            // For STRENGTH, include only compound exercises
                            return exercise.getExerciseType() == ExerciseType.COMPOUND;
                        case WEIGHT_LOSS:
                            // For WEIGHT_LOSS, include both cardio and compound exercises
                            return exercise.getExerciseType() == ExerciseType.CARDIO || exercise.getExerciseType() == ExerciseType.COMPOUND;
                        default:
                            // No exercise type filtering for other goals
                            return true;
                    }
                })
                .map(exercise -> customizeExerciseForGoal(exercise, fitnessGoal))
                .collect(Collectors.toList());
    }

    public boolean isExerciseSuitableForFitnessLevel(Exercise exercise, FitnessLevel userFitnessLevel) {
        // Your logic to filter exercises based on user fitness level
        return switch (userFitnessLevel) {
            case BEGINNER -> exercise.getFitnessLevel() == FitnessLevel.BEGINNER || exercise.getFitnessLevel() == FitnessLevel.INTERMEDIATE;
            case INTERMEDIATE -> exercise.getFitnessLevel() != FitnessLevel.EXPERT; // Intermediate can do beginner to advanced
            case ADVANCED, EXPERT -> true; // Advanced and expert can do any level
            default -> false;
        };
    }

    public Exercise customizeExerciseForGoal(Exercise exercise, FitnessGoal fitnessGoal) {
        // Customization logic based on the goal
        switch (fitnessGoal) {
            case BUILD_MUSCLE:
                // Hypertrophy typically requires moderate to high reps and multiple sets
                exercise.setSets(3); // Standard for muscle growth
                exercise.setReps(10); // Ideal rep range for hypertrophy
                break;
            case WEIGHT_LOSS:
                // Weight loss might focus more on higher reps and cardio
                exercise.setSets(3); // Increase volume slightly
                if (exercise.getExerciseType() == ExerciseType.CARDIO) {
                    exercise.setDuration(Duration.ofMinutes(20)); // Longer duration for cardio exercises
                } else {
                    exercise.setReps(15); // Higher reps for increased calorie burn
                }
                break;
            case STRENGTH:
                // Strength training involves lower reps and higher intensity
                exercise.setSets(5); // More sets for strength
                exercise.setReps(5); // Lower reps with higher weight
                break;
            default:
                // For any other goals, or if no specific goal is set, keep the exercise unchanged
                break;
        }
        return exercise;
    }
    public List<Exercise> getAllExercises() {
        return new ArrayList<>(exercises); // Return a copy of the static list
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}
