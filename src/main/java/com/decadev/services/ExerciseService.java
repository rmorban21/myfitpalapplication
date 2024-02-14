package com.decadev.services;

import com.decadev.entities.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private List<Exercise> exercises = new ArrayList<>();

    @PostConstruct
    public void init() {

        // Core Exercises
        exercises.add(new Exercise("Plank", FitnessLevel.BEGINNER, "Core", "None", 3, null, Duration.ofSeconds(60)));
        exercises.add(new Exercise("Crunches", FitnessLevel.BEGINNER, "Core", "None", 3, 15, null));
        exercises.add(new Exercise("Russian Twists", FitnessLevel.BEGINNER, "Core", "None", 3, 12, null));
        exercises.add(new Exercise("Leg Raises", FitnessLevel.BEGINNER, "Core", "None", 3, 10, null));
        exercises.add(new Exercise("Flutter Kicks", FitnessLevel.BEGINNER, "Core", "None", 3, 20, null));
        exercises.add(new Exercise("Bicycle Crunches", FitnessLevel.BEGINNER, "Core", "None", 3, 12, null));

        // Cardio Exercises
        exercises.add(new Exercise("Jump Squats", FitnessLevel.BEGINNER, "Cardio", "None", 3, 15, null));
        exercises.add(new Exercise("Burpees", FitnessLevel.BEGINNER, "Cardio", "None", 3, 10, null));
        exercises.add(new Exercise("Mountain Climbers", FitnessLevel.BEGINNER, "Cardio", "None", 3, 20, null));
        exercises.add(new Exercise("High Knees", FitnessLevel.BEGINNER, "Cardio", "None", 3, 30, null));
        exercises.add(new Exercise("Jumping Jacks", FitnessLevel.BEGINNER, "Cardio", "None", 3, 30, null));

        // Upper Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Push-up Variations", FitnessLevel.BEGINNER, "Strength", "None", 3, 12, null));
        exercises.add(new Exercise("Chair Dips", FitnessLevel.BEGINNER, "Strength", "None", 3, 12, null));
        exercises.add(new Exercise("Shoulder Taps", FitnessLevel.BEGINNER, "Strength", "None", 3, 12, null));

        // Lower Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Walking Lunges", FitnessLevel.BEGINNER, "Strength", "None", 3, 10, null));
        exercises.add(new Exercise("Lateral Lunges", FitnessLevel.BEGINNER, "Strength", "None", 3, 10, null));
        exercises.add(new Exercise("Single-Leg Deadlifts", FitnessLevel.BEGINNER, "Strength", "None", 3, 10, null));
        exercises.add(new Exercise("Body-weight Squats", FitnessLevel.BEGINNER, "Strength", "None", 3, 10, null));
        exercises.add(new Exercise("Wall Sits", FitnessLevel.BEGINNER, "Strength", "None", 3, null, Duration.ofSeconds(60)));

        // Upper Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Shoulder Press", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Bicep Curls", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Chest Flyes", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Tricep Kickbacks", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));

        // Lower Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Deadlifts", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Squats", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Lunges", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 10, null));

        // Leg Exercises
        exercises.add(new Exercise("Leg Press", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Leg Extension", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Leg Curl", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Hack Squats", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Calf Raises", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 15, null));
        exercises.add(new Exercise("Bulgarian Split Squats", FitnessLevel.INTERMEDIATE, "Strength", "None", 3, 10, null));
        exercises.add(new Exercise("Smith Machine Squats", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Walking Lunges with Dumbbells", FitnessLevel.INTERMEDIATE, "Strength", "Dumbells", 3, 10, null));
        exercises.add(new Exercise("Seated Leg Press", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Box Jumps", FitnessLevel.EXPERT, "Strength", "Box", 3, 10, null));

        // Back Exercises
        exercises.add(new Exercise("Lat Pulldowns", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Seated Cable Rows", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("T-Bar Rows", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Pull-up Variations", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 10, null));
        exercises.add(new Exercise("Barbell Deadlifts", FitnessLevel.EXPERT, "Strength", "Full gym access", 3, 10, null));
        exercises.add(new Exercise("Cable Face Pulls", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 15, null));
        exercises.add(new Exercise("Hyperextensions", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Single Arm Dumbbell Rows", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Wide Grip Pull-ups", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 10, null));
        exercises.add(new Exercise("Bent Over Barbell Rows", FitnessLevel.ADVANCED, "Strength", "Full gym access", 3, 12, null));

        // Chest Exercises
        exercises.add(new Exercise("Cable Crossovers", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));
        exercises.add(new Exercise("Incline Bench Press", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 10, null));
        exercises.add(new Exercise("Decline Bench Press", FitnessLevel.EXPERT, "Strength", "Full gym access", 3, 10, null));
        exercises.add(new Exercise("Chest Dips", FitnessLevel.INTERMEDIATE, "Strength", "Full gym access", 3, 12, null));

        //Shoulder Exercises
        exercises.add(new Exercise("Standing Overhead Press", FitnessLevel.ADVANCED, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Seated Shoulder Press", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Lateral Raises", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Rear Delt Raises", FitnessLevel.INTERMEDIATE, "Strength", "Dumbbells", 3, 15, null));

        // Dynamic Leg Stretches - All Beginner Level
        exercises.add(new Exercise("Deep Lunge: Quad Stretch", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Walking Toe Touches", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Wall Calf Stretch", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Child's Pose", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Shin Boxes", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Frog Stretch", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Deep Squat", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Lying Knee Hugs", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));

        // Dynamic Upper Body Stretches - All Beginner Level
        exercises.add(new Exercise("Arm Circles", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Overhead Tricep Stretch", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Neck Circles", FitnessLevel.BEGINNER, "Stretch", "None", 3, null, Duration.ofSeconds(30)));

        //Full Body SMR
        exercises.add(new Exercise("Full Body Foam Rolling", FitnessLevel.INTERMEDIATE, "Stretch", "Foam Roller", 3, null, Duration.ofMinutes(5L)));

    }

    public List<Exercise> getCustomizedExercises(User user) {
        List<Exercise> accessibleExercises = filterExercisesByGymAccess(user.getGymAccess());
        List<Exercise> levelAppropriateExercises = filterExercisesByFitnessLevel(accessibleExercises, user.getFitnessLevel());

        return levelAppropriateExercises.stream()
                .map(exercise -> customizeExerciseIntensity(exercise, user.getFitnessLevel(), user.getFitnessGoal()))
                .collect(Collectors.toList());
    }

    private Exercise customizeExerciseIntensity(Exercise exercise, FitnessLevel fitnessLevel, FitnessGoal fitnessGoal) {
        // Adjust reps based on fitness goal and level
        if (fitnessGoal == FitnessGoal.BUILD_MUSCLE) {
            exercise.setReps(10);
        } else if (fitnessGoal == FitnessGoal.WEIGHT_LOSS) {
            int reps = fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE ? 10 : 15;
            exercise.setReps(reps);
        } else if (fitnessGoal == FitnessGoal.STRENGTH) {
            exercise.setReps(getRepsForStrengthGoal(fitnessLevel));
        }
        return exercise;
    }

    private List<Exercise> filterExercisesByGymAccess(GymAccess gymAccess) {
        return exercises.stream()
                .filter(exercise -> switch (gymAccess) {
                    case HOME_GYM_NO_WEIGHTS -> exercise.getEquipment().equalsIgnoreCase("None");
                    case HOME_GYM_WITH_WEIGHTS -> exercise.getEquipment().equalsIgnoreCase("None") || exercise.getEquipment().equalsIgnoreCase("Dumbbells") || exercise.getEquipment().equalsIgnoreCase("Foam Roller");
                    case FULL_GYM_ACCESS -> true;
                })
                .collect(Collectors.toList());
    }

    private List<Exercise> filterExercisesByFitnessLevel(List<Exercise> exercises, FitnessLevel userFitnessLevel) {
        return exercises.stream()
                .filter(exercise -> exercise.getFitnessLevel().ordinal() <= userFitnessLevel.ordinal())
                .collect(Collectors.toList());
    }

    private int getRepsForStrengthGoal(FitnessLevel fitnessLevel) {
        return switch (fitnessLevel) {
            case BEGINNER, INTERMEDIATE -> 5; // 5-8 reps for strength training
            case ADVANCED, EXPERT -> 3; // 3-5 reps for higher intensity
        };
    }
}
