package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.enums.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private List<Exercise> exercises = new ArrayList<>();

    /**
     * Initializes the exercise lists.
     * This method would typically populate the various lists (priority, accessory, core, cardio, strength) with exercises.
     * Each exercise would be tailored to fit different fitness goals and levels.
     */
    @PostConstruct
    public void init() {
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

        //Priority Leg Exercises
        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Hack Squat Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Leg Press Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Front Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.LEGS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Walking Dumbbells Lunges").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Dumbbells").sets(3).reps(10).build());

        // Priority Back Exercises
        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Lat Pulldown Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("T-bar machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Bent Over Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(10).build());

        //Priority Chest Exercises
        exercises.add(Exercise.builder().name("Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Flat Bench Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Incline Dumbbell Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());

        //Priority Shoulder Exercises
        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Seated Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Standing Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.PRIORITY).bodyPart(BodyPart.SHOULDERS).equipment("Dumbbells").sets(3).reps(12).build());

        //Accessory Leg Exercises
        exercises.add(Exercise.builder().name("Leg Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Extension Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Curl").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Leg Curl Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Calf Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("Calf Raise Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Bulgarian Split Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Walking Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Lateral Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Single-Leg Deadlifts").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Body-weight Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Wall Sits").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.LEGS).equipment("None").duration("1min").build());

        //Accessory Back Exercises
        exercises.add(Exercise.builder().name("Pull-up").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Cable Face Pulls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Cable Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Single Arm Dumbbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Wide Grip Pull-ups").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Pull-Up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Seated Cable Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.BACK).equipment("Seated Cable Row Machine").sets(3).reps(12).build());

        //Accessory Chest Exercises
        exercises.add(Exercise.builder().name("Push-up Variations").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Chest Flyes").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Cable Crossovers").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Cable Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Decline Bench Press").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Chest Dips").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ACCESSORY).bodyPart(BodyPart.CHEST).equipment("Dip Bar").sets(3).reps(12).build());

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

        // Core Exercises
        exercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).duration("1min").build());
        exercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CORE).bodyPart(BodyPart.CORE).equipment("None").sets(3).reps(12).build());

        // Cardio Exercises
        exercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("StairMaster").duration("15min").build());
        exercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration("30min").build());
        exercises.add(Exercise.builder().name("Incline Treadmill Walk/Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart(BodyPart.CARDIO).equipment("Treadmill").duration("15min").build());

    }

    //TODO: need to revisit adding relevant number of exercises ,priority count + accessory count
    public List<Exercise> getExercisesForDay(FitnessLevel fitnessLevel, FitnessGoal fitnessGoal, Day day) {
        List<Exercise> dayExercises = new ArrayList<>(filterExercisesByLevelAndType(fitnessLevel, ExerciseType.CORE));

        // Check if the day is compatible with the fitness goal
        if (!isDayCompatibleWithGoal(day, fitnessGoal)) {
            // If not compatible, return core exercises only or an empty list based on your requirements
            return Collections.emptyList();
        }

        // If compatible, proceed with filtering exercises based on the fitness goal
        switch (fitnessGoal) {
            case BUILD_MUSCLE -> dayExercises.addAll(filterExercisesForMuscleBuilding(fitnessLevel, day));
            case STRENGTH -> dayExercises.addAll(filterExercisesForStrength(fitnessLevel, day));
            case WEIGHT_LOSS -> dayExercises.addAll(filterExercisesForWeightLoss(fitnessLevel, day));
        }

        return dayExercises;
    }

    private List<Exercise> filterExercisesByLevelAndType(FitnessLevel fitnessLevel, ExerciseType type) {
        return exercises.stream()
                .filter(e -> e.getExerciseType() == type && isExerciseSuitableForFitnessLevel(e, fitnessLevel))
                .collect(Collectors.toList());
    }

    private List<Exercise> filterExercisesForMuscleBuilding(FitnessLevel fitnessLevel, Day day) {
        return filterExercisesByDayAndType(fitnessLevel, day, Arrays.asList(ExerciseType.PRIORITY, ExerciseType.ACCESSORY));
    }

    private List<Exercise> filterExercisesForStrength(FitnessLevel fitnessLevel, Day day) {
        return filterExercisesByDayAndType(fitnessLevel, day, Arrays.asList(ExerciseType.COMPOUND_STRENGTH, ExerciseType.ACCESSORY));
    }

    private List<Exercise> filterExercisesForWeightLoss(FitnessLevel fitnessLevel, Day day) {
        List<Exercise> exercisesForDay = filterExercisesByLevelAndType(fitnessLevel, ExerciseType.CARDIO); // Include cardio for weight loss
        exercisesForDay.addAll(filterExercisesByDayAndType(fitnessLevel, day, Arrays.asList(ExerciseType.PRIORITY, ExerciseType.ACCESSORY)));
        return exercisesForDay;
    }

    private List<Exercise> filterExercisesByDayAndType(FitnessLevel fitnessLevel, Day day, List<ExerciseType> types) {
        List<BodyPart> targetBodyParts = getBodyPartsForDay(day);
        return exercises.stream()
                .filter(e -> types.contains(e.getExerciseType()) &&
                        targetBodyParts.contains(e.getBodyPart()) &&
                        isExerciseSuitableForFitnessLevel(e, fitnessLevel))
                .collect(Collectors.toList());
    }
    /**
     * Determines the relevant body parts for a given day, based on the workout focus.
     * This utility method helps in filtering exercises for strength training.
     */
    private List<BodyPart> getBodyPartsForDay(Day day) {
        // This method returns a list of BodyParts relevant to the given Day.
        // For example, BACK_AND_SHOULDERS day would return [BodyPart.BACK, BodyPart.SHOULDERS].
        switch (day) {
            case BACK_AND_SHOULDERS:
                return Arrays.asList(BodyPart.BACK, BodyPart.SHOULDERS);
            case LEGS:
                return Collections.singletonList(BodyPart.LEGS);
            case CHEST:
                return Collections.singletonList(BodyPart.CHEST);
            case ARMS_CORE:
                return Arrays.asList(BodyPart.ARMS, BodyPart.CORE);
            case PUSH:
                return Arrays.asList(BodyPart.CHEST, BodyPart.SHOULDERS);
            case PULL:
                return Collections.singletonList(BodyPart.BACK);
            case UPPER_BODY:
                return Arrays.asList(BodyPart.BACK, BodyPart.SHOULDERS, BodyPart.CHEST);
            case LOWER_BODY:
                return Collections.singletonList(BodyPart.LEGS);
            default:
                return new ArrayList<>();
        }
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

    private boolean isDayCompatibleWithGoal(Day day, FitnessGoal fitnessGoal) {
        // Define compatibility between days and goals
        switch (fitnessGoal) {
            case BUILD_MUSCLE:
                return EnumSet.of(Day.BACK_AND_SHOULDERS, Day.LEGS, Day.CHEST, Day.ARMS_CORE).contains(day);
            case STRENGTH:
                return EnumSet.of(Day.PUSH, Day.PULL, Day.LEGS).contains(day);
            case WEIGHT_LOSS:
                return EnumSet.of(Day.UPPER_BODY, Day.LOWER_BODY).contains(day);
            default:
                return false;
        }
    }

    /**
     * Provides a comprehensive list of all exercises available in the service, potentially useful for debugging, administration, or logging purposes.
     * @return copy of all exercises.
     */
    public List<Exercise> getAllExercises() {
        // Initialize a new list to hold all exercises
        List<Exercise> allExercises = new ArrayList<>();

        // Add all exercises from each specific list into the allExercises list
        allExercises.addAll(exercises);

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
