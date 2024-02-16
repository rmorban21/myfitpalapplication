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
        exercises.add(new Exercise("Plank", FitnessLevel.BEGINNER, ExerciseType.ISOLATION, "Core", "None", 3, null, Duration.ofSeconds(60)));
        exercises.add(new Exercise("Crunches", FitnessLevel.BEGINNER, ExerciseType.ISOLATION, "Core", "None", 3, 15, null));
        exercises.add(new Exercise("Russian Twists", FitnessLevel.BEGINNER, ExerciseType.ISOLATION,"Core", "None", 3, 12, null));
        exercises.add(new Exercise("Leg Raises", FitnessLevel.BEGINNER, ExerciseType.ISOLATION,"Core", "None", 3, 10, null));
        exercises.add(new Exercise("Flutter Kicks", FitnessLevel.BEGINNER, ExerciseType.ISOLATION,"Core", "None", 3, 20, null));
        exercises.add(new Exercise("Bicycle Crunches", FitnessLevel.BEGINNER, ExerciseType.ISOLATION,"Core", "None", 3, 12, null));

        //TODO: Add cardio machine exercises with durations
        // Cardio Exercises
        exercises.add(new Exercise("Jump Squats", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Legs", "None", 3, 15, null));
        exercises.add(new Exercise("Burpees", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Full Body","None", 3, 10, null));
        exercises.add(new Exercise("Mountain Climbers", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Full Body","None", 3, 20, null));
        exercises.add(new Exercise("High Knees", FitnessLevel.BEGINNER, ExerciseType.CARDIO,  "Legs","None", 3, 30, null));
        exercises.add(new Exercise("Jumping Jacks", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Full Body","None", 3, 30, null));

        // Upper Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Push-up Variations", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Chest", "None", 3, 12, null));
        exercises.add(new Exercise("Chair Dips", FitnessLevel.BEGINNER, ExerciseType.ISOLATION, "Arms", "None", 3, 12, null));
        exercises.add(new Exercise("Shoulder Taps", FitnessLevel.BEGINNER, ExerciseType.ISOLATION, "Shoulders", "None", 3, 12, null));

        // Lower Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Walking Lunges", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Lateral Lunges", FitnessLevel.BEGINNER, ExerciseType.COMPOUND,"Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Single-Leg Deadlifts", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Body-weight Squats", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Wall Sits", FitnessLevel.BEGINNER, ExerciseType.COMPOUND, "Legs", "None",3, null, Duration.ofSeconds(60)));

        // Upper Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Shoulder Press", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Shoulders", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Bicep Curls", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Arms","Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Chest Flyes", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Chest","Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Tricep Kickbacks", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Arms", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Shoulders","Dumbbells", 3, 12, null));

        // Lower Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Deadlifts", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Squats", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND,"Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Lunges", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "Dumbbells", 3, 10, null));

        // Leg Exercises
        exercises.add(new Exercise("Leg Press", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "Leg Press Machine", 3, 12, null));
        exercises.add(new Exercise("Leg Extension", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "Leg Extension Machine", 3, 12, null));
        exercises.add(new Exercise("Leg Curl", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs","Leg Curl Machine", 3, 12, null));
        exercises.add(new Exercise("Hack Squats", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Legs","Hack Squat Machine", 3, 12, null));
        exercises.add(new Exercise("Back Squats", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Legs","Barbell", 3, 12, null));
        exercises.add(new Exercise("Calf Raises", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Legs", "Calf Raise Machine", 3, 15, null));
        exercises.add(new Exercise("Bulgarian Split Squats", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Smith Machine Squats", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Legs", "Smith Machine", 3, 12, null));
        exercises.add(new Exercise("Walking Lunges with Dumbbells", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Box Jumps", FitnessLevel.EXPERT, ExerciseType.COMPOUND, "Legs", "Box", 3, 10, null));

        // Back Exercises
        exercises.add(new Exercise("Lat Pulldowns", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Back", "Lat Pulldown Machine", 3, 12, null));
        exercises.add(new Exercise("Seated Cable Rows", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Back","Seated Cable Row Machine", 3, 12, null));
        exercises.add(new Exercise("T-Bar Rows", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Back", "T-bar machine", 3, 12, null));
        exercises.add(new Exercise("Pull-up Variations", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Back", "Pull-up Bar or Machine", 3, 10, null));
        exercises.add(new Exercise("Barbell Deadlifts", FitnessLevel.EXPERT, ExerciseType.COMPOUND, "Back", "Barbell", 3, 10, null));
        exercises.add(new Exercise("Cable Face Pulls", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Back", "Cable Machine", 3, 15, null));
        exercises.add(new Exercise("Hyperextensions", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Back", "Hyperextension Machine", 3, 12, null));
        exercises.add(new Exercise("Single Arm Dumbbell Rows", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Back", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Wide Grip Pull-ups", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Back","Pull-Up Bar or Machine", 3, 10, null));
        exercises.add(new Exercise("Bent Over Barbell Rows", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Back", "Barbell", 3, 12, null));

        // Chest Exercises
        exercises.add(new Exercise("Cable Crossovers", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Chest", "Cable Machine", 3, 12, null));
        exercises.add(new Exercise("Incline Bench Press", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Chest", "Bench Press", 3, 10, null));
        exercises.add(new Exercise("Decline Bench Press", FitnessLevel.EXPERT, ExerciseType.COMPOUND, "Chest","Bench Press", 3, 10, null));
        exercises.add(new Exercise("Chest Dips", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND,"Chest", "Dip Bar", 3, 12, null));

        //Shoulder Exercises
        exercises.add(new Exercise("Standing Military Overhead Press", FitnessLevel.ADVANCED, ExerciseType.COMPOUND, "Shoulders", "Barbell", 3, 12, null));
        exercises.add(new Exercise("Seated Shoulder Press", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Shoulders", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Lateral Raises", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Shoulders","Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION,"Shoulders", "Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Rear Delt Raises", FitnessLevel.INTERMEDIATE, ExerciseType.ISOLATION, "Shoulders","Dumbbells", 3, 15, null));
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
    //TODO: find possible implementation
    public List<Exercise> filterExercisesByGoal(List<Exercise> exercises, FitnessGoal goal) {
        return switch (goal) {
            case WEIGHT_LOSS -> exercises.stream().filter(e -> ExerciseType.CARDIO.equals(e.getExerciseType())).collect(Collectors.toList());
            case BUILD_MUSCLE -> exercises.stream().filter(e -> ExerciseType.COMPOUND.equals(e.getExerciseType())).collect(Collectors.toList());
            case STRENGTH -> exercises.stream().filter(e -> ExerciseType.COMPOUND.equals(e.getExerciseType()) || ExerciseType.ISOLATION.equals(e.getExerciseType())).collect(Collectors.toList());
            default -> exercises;
        };
    }

    private List<Exercise> filterExercisesByGymAccess(GymAccess gymAccess) {
        return exercises.stream()
                .filter(exercise -> switch (gymAccess) {
                    case HOME_GYM_NO_WEIGHTS -> "None".equalsIgnoreCase(exercise.getEquipment());
                    case HOME_GYM_WITH_WEIGHTS -> "None".equalsIgnoreCase(exercise.getEquipment()) || "Dumbbells".equalsIgnoreCase(exercise.getEquipment()) || "Foam Roller".equalsIgnoreCase(exercise.getEquipment());
                    case FULL_GYM_ACCESS -> true;
                })
                .collect(Collectors.toList());
    }

    private List<Exercise> filterExercisesByFitnessLevel(List<Exercise> exercises, FitnessLevel userFitnessLevel) {
        return exercises.stream()
                .filter(exercise -> exercise.getFitnessLevel().compareTo(userFitnessLevel) <= 0)
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
    private Exercise customizeExerciseIntensity(Exercise exercise, FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        if (fitnessGoal == FitnessGoal.BUILD_MUSCLE) {
            exercise.setReps(10);
        } else if (fitnessGoal == FitnessGoal.WEIGHT_LOSS) {
            exercise.setReps(fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE ? 10 : 15);
        } else if (fitnessGoal == FitnessGoal.STRENGTH) {
            exercise.setReps(getRepsForStrengthGoal(fitnessLevel));
        }
        return exercise;
    }

    //TODO: find possible implementation
    public int calculateSessionsPerWeek(Integer availability, FitnessGoal goal) {
        int baseSessionCount = availability != null && availability >= 2 ? availability / 2 : 2;
        return switch (goal) {
            case BUILD_MUSCLE -> Math.min(baseSessionCount, 5);
            case WEIGHT_LOSS -> Math.min(baseSessionCount, 4);
            case STRENGTH -> Math.min(baseSessionCount, 3);
            default -> baseSessionCount;
        };
    }

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

}
