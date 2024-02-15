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
        exercises.add(new Exercise("Plank", FitnessLevel.BEGINNER, "Isolation", "Core", "None", 3, null, Duration.ofSeconds(60)));
        exercises.add(new Exercise("Crunches", FitnessLevel.BEGINNER, "Isolation", "Core", "None", 3, 15, null));
        exercises.add(new Exercise("Russian Twists", FitnessLevel.BEGINNER, "Isolation","Core", "None", 3, 12, null));
        exercises.add(new Exercise("Leg Raises", FitnessLevel.BEGINNER, "Isolation","Core", "None", 3, 10, null));
        exercises.add(new Exercise("Flutter Kicks", FitnessLevel.BEGINNER, "Isolation","Core", "None", 3, 20, null));
        exercises.add(new Exercise("Bicycle Crunches", FitnessLevel.BEGINNER, "Isolation","Core", "None", 3, 12, null));

        // Cardio Exercises
        exercises.add(new Exercise("Jump Squats", FitnessLevel.BEGINNER, "Cardio", "Legs", "None", 3, 15, null));
        exercises.add(new Exercise("Burpees", FitnessLevel.BEGINNER, "Cardio", "Full Body","None", 3, 10, null));
        exercises.add(new Exercise("Mountain Climbers", FitnessLevel.BEGINNER, "Cardio", "Full Body","None", 3, 20, null));
        exercises.add(new Exercise("High Knees", FitnessLevel.BEGINNER, "Cardio", "Legs","None", 3, 30, null));
        exercises.add(new Exercise("Jumping Jacks", FitnessLevel.BEGINNER, "Cardio", "Full Body","None", 3, 30, null));

        // Upper Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Push-up Variations", FitnessLevel.BEGINNER, "Compound", "Chest", "None", 3, 12, null));
        exercises.add(new Exercise("Chair Dips", FitnessLevel.BEGINNER, "Isolation", "Arms", "None", 3, 12, null));
        exercises.add(new Exercise("Shoulder Taps", FitnessLevel.BEGINNER, "Isolation", "Shoulders", "None", 3, 12, null));

        // Lower Body Exercises (Home Gym, No Weights)
        exercises.add(new Exercise("Walking Lunges", FitnessLevel.BEGINNER, "Compound", "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Lateral Lunges", FitnessLevel.BEGINNER, "Compound", "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Single-Leg Deadlifts", FitnessLevel.BEGINNER, "Compound", "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Body-weight Squats", FitnessLevel.BEGINNER, "Compound", "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Wall Sits", FitnessLevel.BEGINNER, "Compound", "Legs", "None",3, null, Duration.ofSeconds(60)));

        // Upper Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Shoulder Press", FitnessLevel.INTERMEDIATE, "Compound", "Shoulders", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Bicep Curls", FitnessLevel.INTERMEDIATE, "Isolation", "Arms","Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Chest Flyes", FitnessLevel.INTERMEDIATE, "Isolation", "Chest","Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Tricep Kickbacks", FitnessLevel.INTERMEDIATE, "Isolation", "Arms", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, "Isolation", "Shoulders","Dumbbells", 3, 12, null));

        // Lower Body Exercises (Home Gym, With Weights)
        exercises.add(new Exercise("Dumbbell Deadlifts", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Squats", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Dumbbell Lunges", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Dumbbells", 3, 10, null));

        // Leg Exercises
        exercises.add(new Exercise("Leg Press", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Leg Press Machine", 3, 12, null));
        exercises.add(new Exercise("Leg Extension", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Leg Extension Machine", 3, 12, null));
        exercises.add(new Exercise("Leg Curl", FitnessLevel.INTERMEDIATE, "Compound", "Legs","Leg Curl Machine", 3, 12, null));
        exercises.add(new Exercise("Hack Squats", FitnessLevel.ADVANCED, "Compound", "Legs","Hack Squat Machine", 3, 12, null));
        exercises.add(new Exercise("Back Squats", FitnessLevel.ADVANCED, "Compound", "Legs","Barbell", 3, 12, null));
        exercises.add(new Exercise("Calf Raises", FitnessLevel.INTERMEDIATE, "Isolation", "Legs", "Calf Raise Machine", 3, 15, null));
        exercises.add(new Exercise("Bulgarian Split Squats", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "None", 3, 10, null));
        exercises.add(new Exercise("Smith Machine Squats", FitnessLevel.ADVANCED, "Compound", "Legs", "Smith Machine", 3, 12, null));
        exercises.add(new Exercise("Walking Lunges with Dumbbells", FitnessLevel.INTERMEDIATE, "Compound", "Legs", "Dumbbells", 3, 10, null));
        exercises.add(new Exercise("Box Jumps", FitnessLevel.EXPERT, "Compound", "Legs", "Box", 3, 10, null));

        // Back Exercises
        exercises.add(new Exercise("Lat Pulldowns", FitnessLevel.INTERMEDIATE, "Compound", "Back", "Lat Pulldown Machine", 3, 12, null));
        exercises.add(new Exercise("Seated Cable Rows", FitnessLevel.INTERMEDIATE, "Compound", "Back","Seated Cable Row Machine", 3, 12, null));
        exercises.add(new Exercise("T-Bar Rows", FitnessLevel.ADVANCED, "Compound", "Back", "T-bar machine", 3, 12, null));
        exercises.add(new Exercise("Pull-up Variations", FitnessLevel.ADVANCED, "Compound", "Back", "Pull-up Bar or Machine", 3, 10, null));
        exercises.add(new Exercise("Barbell Deadlifts", FitnessLevel.EXPERT, "Compound", "Back", "Barbell", 3, 10, null));
        exercises.add(new Exercise("Cable Face Pulls", FitnessLevel.INTERMEDIATE, "Compound", "Back", "Cable Machine", 3, 15, null));
        exercises.add(new Exercise("Hyperextensions", FitnessLevel.ADVANCED, "Compound", "Back", "Hyperextension Machine", 3, 12, null));
        exercises.add(new Exercise("Single Arm Dumbbell Rows", FitnessLevel.INTERMEDIATE, "Compound", "Back", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Wide Grip Pull-ups", FitnessLevel.ADVANCED, "Compound", "Back","Pull-Up Bar or Machine", 3, 10, null));
        exercises.add(new Exercise("Bent Over Barbell Rows", FitnessLevel.ADVANCED, "Compound", "Back", "Barbell", 3, 12, null));

        // Chest Exercises
        exercises.add(new Exercise("Cable Crossovers", FitnessLevel.ADVANCED, "Compound", "Chest", "Cable Machine", 3, 12, null));
        exercises.add(new Exercise("Incline Bench Press", FitnessLevel.INTERMEDIATE, "Compound", "Chest", "Bench Press", 3, 10, null));
        exercises.add(new Exercise("Decline Bench Press", FitnessLevel.EXPERT, "Compound", "Chest","Bench Press", 3, 10, null));
        exercises.add(new Exercise("Chest Dips", FitnessLevel.INTERMEDIATE, "Compound", "Chest", "Dip Bar", 3, 12, null));

        //Shoulder Exercises
        exercises.add(new Exercise("Standing Military Overhead Press", FitnessLevel.ADVANCED, "Compound", "Shoulders", "Barbell", 3, 12, null));
        exercises.add(new Exercise("Seated Shoulder Press", FitnessLevel.INTERMEDIATE, "Compound", "Shoulders", "Dumbbells", 3, 12, null));
        exercises.add(new Exercise("Dumbbell Lateral Raises", FitnessLevel.INTERMEDIATE, "Isolation", "Shoulders","Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Front Raises", FitnessLevel.INTERMEDIATE, "Isolation", "Dumbbells", 3, 15, null));
        exercises.add(new Exercise("Dumbbell Rear Delt Raises", FitnessLevel.INTERMEDIATE, "Isolation", "Dumbbells", 3, 15, null));

        // Dynamic Leg Stretches - All Beginner Level
        exercises.add(new Exercise("Deep Lunge: Quad Stretch", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Walking Toe Touches", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None",  3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Wall Calf Stretch", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None",  3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Child's Pose", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Shin Boxes", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Frog Stretch", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None",  3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Deep Squat", FitnessLevel.BEGINNER, "Stretch", "Lower Body","None",  3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Lying Knee Hugs", FitnessLevel.BEGINNER, "Stretch", "Lower Body", "None",  3, null, Duration.ofSeconds(30)));

        // Dynamic Upper Body Stretches - All Beginner Level
        exercises.add(new Exercise("Arm Circles", FitnessLevel.BEGINNER, "Stretch", "Upper Body", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Overhead Tricep Stretch", FitnessLevel.BEGINNER, "Stretch", "Upper Body", "None", 3, null, Duration.ofSeconds(30)));
        exercises.add(new Exercise("Neck Circles", FitnessLevel.BEGINNER, "Stretch", "Upper Body","None", 3, null, Duration.ofSeconds(30)));

        //Full Body SMR
        exercises.add(new Exercise("Full Body Foam Rolling", FitnessLevel.INTERMEDIATE, "Stretch", "Full Body", "Foam Roller", 3, null, Duration.ofMinutes(5L)));

    }

    public List<Exercise> generatePersonalizedWorkoutPlan(User user) {
        // Entry point to generate a workout plan based on user preferences.
        switch (user.getFitnessGoal()) {
            case BUILD_MUSCLE:
                return buildMusclePlan(user);
            case WEIGHT_LOSS:
                return weightLossPlan(user);
            case STRENGTH:
                return strengthPlan(user);
            default:
                return new ArrayList<>();
        }
    }

    // Muscle Building Workout Plan Logic
    private List<Exercise> buildMusclePlan(User user) {
        List<Exercise> plan = new ArrayList<>();
        int sessionsPerWeek = user.getAvailability(); // Assuming this is the number of days available for training

        // Allocate sessions based on muscle group frequency and recovery needs
        Map<String, Integer> frequencyMap = calculateFrequencyMap(sessionsPerWeek);

        frequencyMap.forEach((bodyPart, frequency) -> {
            for (int i = 0; i < frequency; i++) {
                plan.addAll(filterExercisesByBodyPart(bodyPart, user));
            }
        });

        // Adjust for volume and intensity
        plan.forEach(exercise -> adjustVolumeAndIntensityForMuscleGrowth(exercise, user.getFitnessLevel()));

        // Consider user's equipment availability
        plan.removeIf(exercise -> !isEquipmentAccessible(user.getGymAccess(), exercise.getEquipment()));

        return plan;
    }

    private Map<String, Integer> calculateFrequencyMap(int sessionsPerWeek) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        // Example logic, adjust based on principles and sessionsPerWeek
        if (sessionsPerWeek >= 5) {
            frequencyMap.put("Legs", 2);
            frequencyMap.put("Back", 2);
            frequencyMap.put("Chest", 2);
            frequencyMap.put("Arms", 1);
            frequencyMap.put("Core", 2);
        } else {
            // Adjust the frequency based on available sessions
            frequencyMap.put("Legs", 1);
            frequencyMap.put("Back", 1);
            frequencyMap.put("Chest", 1);
            frequencyMap.put("Arms", 1);
            frequencyMap.put("Core", 1);
        }
        return frequencyMap;
    }

    private void adjustVolumeAndIntensityForMuscleGrowth(Exercise exercise, FitnessLevel fitnessLevel) {
        // Example adjustments, should be refined
        if (fitnessLevel == FitnessLevel.BEGINNER) {
            exercise.setSets(Math.min(exercise.getSets(), 3)); // Cap sets for beginners to avoid overtraining
            exercise.setReps(8); // Hypertrophy rep range
        } else {
            exercise.setSets(Math.min(exercise.getSets(), 4)); // Slightly higher volume for more experienced users
            exercise.setReps(10); // Within hypertrophy range
        }
    }

    //TODO: TBD

    // Weight Loss Workout Plan Logic
    //TODO: Review
    private List<Exercise> weightLossPlan(User user) {
        return exercises.stream()
                .filter(e -> "Cardio".equals(e.getExerciseType()) || "High Intensity".equals(e.getExerciseType()))
                .filter(e -> isEquipmentAccessible(user.getGymAccess(), e.getEquipment()))
                .limit(user.getAvailability())
                .collect(Collectors.toList());
    }

    // Strength Training Workout Plan Logic
    //TODO: Review
    private List<Exercise> strengthPlan(User user) {
        // Example: Heavy lifting days mixed with lighter, high-rep days
        List<Exercise> plan = new ArrayList<>();
        plan.addAll(filterExercisesByBodyPartForStrength("Compound", user)); // Example method for compound lifts
        plan.addAll(filterExercisesForMixedSessions(user, 3)); // Lighter days based on availability
        return plan;
    }

    // TODO: Revise ??
    private List<Exercise> filterExercisesByBodyPart(String bodyPart, User user) {
        return exercises.stream()
                .filter(e -> bodyPart.equals(e.getBodyPart()))
                .filter(e -> isEquipmentAccessible(user.getGymAccess(), e.getEquipment()))
                .collect(Collectors.toList());
    }

    //TODO: Review, determine if needed, method exists to determine rep ranges
    private List<Exercise> filterExercisesByBodyPartForStrength(String exerciseType, User user) {
        // Similar to filterExercisesByBodyPart but focused on strength
        return new ArrayList<>(); // Placeholder
    }

    //TODO: Review, determine if needed, method exists to determine rep ranges
    private List<Exercise> filterExercisesForMixedSessions(User user, int sessions) {
        // Logic to mix exercises for users with limited availability
        return new ArrayList<>(); // Placeholder
    }

    private boolean isEquipmentAccessible(GymAccess gymAccess, String equipment) {
        return switch (gymAccess) {
            case HOME_GYM_NO_WEIGHTS -> "None".equalsIgnoreCase(equipment);
            case HOME_GYM_WITH_WEIGHTS -> !"Full gym access".equalsIgnoreCase(equipment);
            case FULL_GYM_ACCESS -> true;
        };
    }

    //TODO: revise to ensure logic aligns with scientific principles
    private List<Exercise> filterExercisesByGoal(List<Exercise> exercises, FitnessGoal goal) {
        // Adjusted to use exerciseType for filtering
        return switch (goal) {
            case WEIGHT_LOSS -> exercises.stream()
                    .filter(e -> "Cardio".equals(e.getExerciseType()) || "Compound".equals(e.getExerciseType())) // Assuming you categorize exercises somehow
                    .collect(Collectors.toList());
            case BUILD_MUSCLE -> exercises.stream()
                    .filter(e -> "Compound".equals(e.getExerciseType())) // This assumes you have a way to identify compound exercises
                    .collect(Collectors.toList());
            case STRENGTH -> exercises; // No filtering, but you might adjust intensity in another method
            default -> exercises;
        };
    }

    //TODO: revise to handle varType and varName inconsistencies, ensure logic supports goal-specific session frequency
    private int calculateSessionsPerWeek(Integer availabilityHours, FitnessGoal goal) {
        int baseSessionCount = availabilityHours != null && availabilityHours >= 2 ? availabilityHours / 2 : 2;
        switch (goal) {
            case BUILD_MUSCLE:
                // Assuming more frequent sessions might be beneficial, up to a point.
                return Math.min(baseSessionCount, 5);
            case WEIGHT_LOSS:
                // Weight loss could benefit from both high-frequency cardio and strength sessions.
                return Math.min(baseSessionCount, 4);
            case STRENGTH:
                // Strength training often requires more recovery time.
                return Math.min(baseSessionCount, 3);
            default:
                return baseSessionCount;
        }
    }

    //TODO: Revise
    private Duration calculateSessionDuration(FitnessLevel level, int availabilityHours) {
        // Simplified logic to adjust session duration based on fitness level and total availability
        if (level == FitnessLevel.ADVANCED || level == FitnessLevel.EXPERT) {
            return Duration.ofMinutes(Math.min(60, availabilityHours * 30)); // Longer sessions for higher levels
        } else {
            return Duration.ofMinutes(30); // Default to 30-minute sessions for lower levels
        }
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

    //TODO : Implement
    private int determineSessionLength(int availbility) {
        return availbility;
    }
}
