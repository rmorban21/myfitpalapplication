package com.decadev.services;

import com.decadev.entities.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private List<Exercise> exercises = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Core Exercises
        exercises.add(Exercise.builder().name("Plank").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).duration(Duration.ofSeconds(60)).build());
        exercises.add(Exercise.builder().name("Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Russian Twists").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Raises").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Flutter Kicks").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(20).build());
        exercises.add(Exercise.builder().name("Bicycle Crunches").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Core").equipment("None").sets(3).reps(12).build());

        // Cardio Exercises
        exercises.add(Exercise.builder().name("StairMaster").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("StairMaster").duration(Duration.ofMinutes(15)).build());
        exercises.add(Exercise.builder().name("Low-Intensity Run").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(30)).build());
        exercises.add(Exercise.builder().name("Incline Treadmill Walk").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("Treadmill").duration(Duration.ofMinutes(15)).build());
        exercises.add(Exercise.builder().name("Jump Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("None").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Burpees").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Mountain Climbers").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(20).build());
        exercises.add(Exercise.builder().name("High Knees").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Legs").equipment("None").sets(3).reps(30).build());
        exercises.add(Exercise.builder().name("Jumping Jacks").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.CARDIO).bodyPart("Full Body").equipment("None").sets(3).reps(30).build());

        // Upper Body Exercises (Home Gym, No Weights)
        exercises.add(Exercise.builder().name("Push-up Variations").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Chair Dips").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("None").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Shoulder Taps").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("None").sets(3).reps(12).build());

        // Lower Body Exercises (Home Gym, No Weights)
        exercises.add(Exercise.builder().name("Walking Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Lateral Lunges").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Single-Leg Deadlifts").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Body-weight Squats").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Wall Sits").fitnessLevel(FitnessLevel.BEGINNER).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").duration(Duration.ofSeconds(60)).build());

        // Upper Body Exercises (Home Gym, With Weights)
        exercises.add(Exercise.builder().name("Dumbbell Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Bicep Curls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Chest Flyes").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Chest").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Tricep Kickbacks").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Arms").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Front Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(12).build());

        // Lower Body Exercises (Home Gym, With Weights)
        exercises.add(Exercise.builder().name("Dumbbell Deadlifts").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Dumbbells").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Dumbbell Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Dumbbells").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Dumbbell Lunges").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Dumbbells").sets(3).reps(10).build());

        // Leg Exercises
        exercises.add(Exercise.builder().name("Leg Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Press Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Extension").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Extension Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Leg Curl").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Leg Curl Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Hack Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Hack Squat Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Back Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Calf Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Legs").equipment("Calf Raise Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Bulgarian Split Squats").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("None").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Smith Machine Squats").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Smith Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Walking Lunges with Dumbbells").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Dumbbells").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Box Jumps").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Legs").equipment("Box").sets(3).reps(10).build());

        // Back Exercises
        exercises.add(Exercise.builder().name("Lat Pulldowns").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Lat Pulldown Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Seated Cable Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Seated Cable Row Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("T-Bar Rows").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("T-bar machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Pull-up Variations").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Pull-up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Barbell Deadlifts").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Cable Face Pulls").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Cable Machine").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Hyperextensions").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Hyperextension Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Single Arm Dumbbell Rows").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Wide Grip Pull-ups").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Pull-Up Bar or Machine").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Bent Over Barbell Rows").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Back").equipment("Barbell").sets(3).reps(12).build());

        // Chest Exercises
        exercises.add(Exercise.builder().name("Cable Crossovers").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Cable Machine").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Incline Bench Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Decline Bench Press").fitnessLevel(FitnessLevel.EXPERT).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Bench Press").sets(3).reps(10).build());
        exercises.add(Exercise.builder().name("Chest Dips").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Chest").equipment("Dip Bar").sets(3).reps(12).build());

        // Shoulder Exercises
        exercises.add(Exercise.builder().name("Standing Military Overhead Press").fitnessLevel(FitnessLevel.ADVANCED).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Barbell").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Seated Shoulder Press").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.COMPOUND).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(12).build());
        exercises.add(Exercise.builder().name("Dumbbell Lateral Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Dumbbell Front Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());
        exercises.add(Exercise.builder().name("Dumbbell Rear Delt Raises").fitnessLevel(FitnessLevel.INTERMEDIATE).exerciseType(ExerciseType.ISOLATION).bodyPart("Shoulders").equipment("Dumbbells").sets(3).reps(15).build());
    }

    public List<Exercise> filterExercisesByBodyPart(String bodyPart, User user) {
        return exercises.stream()
                .filter(e -> bodyPart.equalsIgnoreCase(e.getBodyPart()))
                .filter(e -> isEquipmentAccessible(user.getGymAccess(), e.getEquipment()))
                .collect(Collectors.toList());
    }

    public List<Exercise> filterExercisesByBodyPartForStrength(String exerciseType, User user) {
        return filterExercisesByBodyPart(exerciseType, user).stream()
                .filter(e -> ExerciseType.COMPOUND == e.getExerciseType())
                .collect(Collectors.toList());
    }

    public List<Exercise> filterExercisesForMixedSessions(User user, int sessions) {
        List<Exercise> mixedExercises = new ArrayList<>();
        if (sessions <= 2) {
            mixedExercises.addAll(filterExercisesByBodyPart("Legs", user).stream().limit(2).collect(Collectors.toList()));
            mixedExercises.addAll(filterExercisesByBodyPart("Core", user).stream().limit(2).collect(Collectors.toList()));
        } else {
            exercises.stream()
                    .filter(e -> isEquipmentAccessible(user.getGymAccess(), e.getEquipment()))
                    .limit(sessions * 2) // Assuming 2 exercises per session
                    .forEach(mixedExercises::add);
        }
        return mixedExercises;
    }

    public List<Exercise> filterExercisesByGoal(List<Exercise> exercises, FitnessGoal goal) {
        return switch (goal) {
            case WEIGHT_LOSS -> exercises.stream()
                    .filter(e -> ExerciseType.CARDIO.equals(e.getExerciseType()) || ExerciseType.COMPOUND.equals(e.getExerciseType()))
                    .collect(Collectors.toList());
            case BUILD_MUSCLE, STRENGTH -> exercises.stream()
                    .filter(e -> ExerciseType.COMPOUND.equals(e.getExerciseType()) || ExerciseType.ISOLATION.equals(e.getExerciseType()))
                    .collect(Collectors.toList());
            default -> exercises;
        };
    }

    public List<Exercise> filterExercisesByGymAccess(GymAccess gymAccess) {
        if (gymAccess == null) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }

        return exercises.stream()
                .filter(exercise -> {
                    switch (gymAccess) {
                        case HOME_GYM_NO_WEIGHTS:
                            return "None".equalsIgnoreCase(exercise.getEquipment());
                        case HOME_GYM_WITH_WEIGHTS:
                            return "None".equalsIgnoreCase(exercise.getEquipment()) || "Dumbbells".equalsIgnoreCase(exercise.getEquipment()) || "Foam Roller".equalsIgnoreCase(exercise.getEquipment());
                        case FULL_GYM_ACCESS:
                            return true;
                        default:
                            return false; // Ensuring all paths return a value
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Exercise> filterExercisesByFitnessLevel(List<Exercise> exercises, FitnessLevel userFitnessLevel) {
        if (exercises == null || userFitnessLevel == null) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }

        return exercises.stream()
                .filter(exercise -> exercise != null && exercise.getFitnessLevel() != null && exercise.getFitnessLevel().compareTo(userFitnessLevel) <= 0)
                .collect(Collectors.toList());
    }

    public boolean isEquipmentAccessible(GymAccess gymAccess, String equipment) {
        return switch (gymAccess) {
            case HOME_GYM_NO_WEIGHTS -> "None".equalsIgnoreCase(equipment);
            case HOME_GYM_WITH_WEIGHTS -> !"Full gym access".equalsIgnoreCase(equipment);
            case FULL_GYM_ACCESS -> true;
            default -> false; // Ensuring all paths return a value
        };
    }
    public Exercise customizeExerciseIntensity(Exercise exercise, FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        if (fitnessGoal == FitnessGoal.BUILD_MUSCLE) {
            exercise.setReps(10);
        } else if (fitnessGoal == FitnessGoal.WEIGHT_LOSS) {
            exercise.setReps(fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE ? 10 : 15);
        } else if (fitnessGoal == FitnessGoal.STRENGTH) {
            exercise.setReps(getRepsForStrengthGoal(fitnessLevel));
        }
        return exercise;
    }

    public int calculateSessionsPerWeek(Integer availability, FitnessGoal goal) {
        int baseSessionCount = availability != null && availability >= 2 ? (availability + 1) / 2 : 2;
        return switch (goal) {
            case BUILD_MUSCLE -> Math.min(baseSessionCount, 5);
            case WEIGHT_LOSS -> Math.min(baseSessionCount, 4);
            case STRENGTH -> Math.min(baseSessionCount, 3);
            default -> baseSessionCount;
        };
    }
    //TODO: possibility for removal if unused moving forward
    public Duration calculateSessionDuration(FitnessLevel level, int availability) {
        int baseDuration = 30; // Default duration in minutes
        int additionalMinutes = (availability - 2) * 5; // Adjusting based on availability
        int calculatedDuration = level == FitnessLevel.ADVANCED || level == FitnessLevel.EXPERT
                ? Math.min(60, baseDuration + additionalMinutes)
                : baseDuration;
        return Duration.ofMinutes(calculatedDuration);
    }

    private int getRepsForStrengthGoal(FitnessLevel fitnessLevel) {
        return switch (fitnessLevel) {
            case BEGINNER, INTERMEDIATE -> 5;
            case ADVANCED, EXPERT -> 3;
        };
    }

    public List<Exercise> getCustomizedExercises(User user) {
        List<Exercise> accessibleExercises = filterExercisesByGymAccess(user.getGymAccess());
        return filterExercisesByFitnessLevel(accessibleExercises, user.getFitnessLevel()).stream()
                .map(exercise -> customizeExerciseIntensity(exercise, user.getFitnessLevel(), user.getFitnessGoal()))
                .collect(Collectors.toList());
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}
