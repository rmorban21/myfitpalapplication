package com.decadev.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.decadev.entities.*;
import com.decadev.repositories.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class WorkoutPlanService {
    @Autowired
    private  ExerciseService  exerciseService;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    private static final String GENERAL_ADVICE = "Remember to stretch before and after your workout...";


    public WorkoutPlan generateAndSaveWorkoutPlan(User user) {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        setWorkoutPlanDetails(workoutPlan, user);
        workoutPlan.setExercises(generateExercisesBasedOnGoal(user));
        workoutPlan.setGeneralAdvice(GENERAL_ADVICE);

        Duration sessionDuration = exerciseService.calculateSessionDuration(user.getFitnessLevel(), user.getAvailability());

        return workoutPlanRepository.save(workoutPlan);
    }

    private void setWorkoutPlanDetails(WorkoutPlan workoutPlan, User user) {
        workoutPlan.setUserId(user.getUserId());
        workoutPlan.setFitnessGoal(user.getFitnessGoal());
        workoutPlan.setFitnessLevel(user.getFitnessLevel());
        workoutPlan.setGymAccess(user.getGymAccess());
        workoutPlan.setAvailability(user.getAvailability());
    }

    private List<Exercise> generateExercisesBasedOnGoal(User user) {
        return exerciseService.getCustomizedExercises(user);
    }

    // Implement other methods like createOrUpdateWorkoutPlan, updateUserWorkoutPlan, etc., following similar patterns

    private String generateGeneralAdvice() {
        return "Remember to stretch before and after your workout for at least 5 minutes. " +
                "Dynamic stretches are recommended before starting to prepare your muscles. " +
                "After your workout, use static stretches to aid in recovery. " +
                "Incorporate full-body foam rolling (SMR) to help release muscle tightness and improve flexibility. " +
                "Visit our website for detailed guides and tutorials on effective stretching and SMR techniques.";
    }


    private List<Exercise> createBuildMusclePlan(User user) {
        List<Exercise> plan = new ArrayList<>();
        int sessionsPerWeek = user.getAvailability(); // Assuming this is the number of days available for training

        // Allocate sessions based on muscle group frequency and recovery needs
        Map<String, Integer> frequencyMap = calculateFrequencyMap(sessionsPerWeek);

        frequencyMap.forEach((bodyPart, frequency) -> {
            for (int i = 0; i < frequency; i++) {
                plan.addAll(exerciseService.filterExercisesByBodyPart(bodyPart, user));
            }
        });

        // Adjust for volume and intensity
        plan.forEach(exercise -> adjustVolumeAndIntensityForMuscleGrowth(exercise, user.getFitnessLevel()));

        // Consider user's equipment availability
        plan.removeIf(exercise -> !exerciseService.isEquipmentAccessible(user.getGymAccess(), exercise.getEquipment()));

        return plan;
    }

    private List<Exercise> createWeightLossPlan(User user) {
        // Fetch all exercises first, then filter based on the type within this method
        List<Exercise> allExercises = exerciseService.getCustomizedExercises(user);

        // Filter for Cardio exercises
        List<Exercise> cardioExercises = allExercises.stream()
                .filter(e -> e.getExerciseType() == ExerciseType.CARDIO)
                .collect(Collectors.toList());

        // Filter for Compound exercises, which are beneficial for strength training
        List<Exercise> strengthExercises = allExercises.stream()
                .filter(e -> e.getExerciseType() == ExerciseType.COMPOUND)
                .collect(Collectors.toList());

        // Assume a balanced distribution of exercises for weight loss
        List<Exercise> weightLossExercises = new ArrayList<>();
        weightLossExercises.addAll(cardioExercises); // Include all filtered cardio exercises
        weightLossExercises.addAll(strengthExercises); // Include all filtered strength exercises

        return weightLossExercises;
    }

    private List<Exercise> createStrengthPlan(User user) {
        // Example: Heavy lifting days mixed with lighter, high-rep days
        List<Exercise> plan = new ArrayList<>();
        plan.addAll(exerciseService.filterExercisesByBodyPartForStrength("Compound", user)); // Example method for compound lifts
        plan.addAll(exerciseService.filterExercisesForMixedSessions(user, 3)); // Lighter days based on availability
        return plan;
    }


    public WorkoutPlan updateUserWorkoutPlan(User user) {
        return workoutPlanRepository.findWorkoutPlanByUserId(user.getUserId())
                .map(existingPlan -> {
                    WorkoutPlan updatedPlan = generateAndSaveWorkoutPlan(user);
                    updatedPlan.setPlanId(existingPlan.getPlanId()); // Keep the same ID
                    return workoutPlanRepository.updateWorkoutPlan(updatedPlan);
                })
                .orElseGet(() -> generateAndSaveWorkoutPlan(user)); // Generate a new plan if not found
    }


    private Map<String, Integer> calculateFrequencyMap(int sessionsPerWeek) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        if (sessionsPerWeek >= 5) {
            frequencyMap.put("Legs", 2); // Larger muscle groups get more frequency
            frequencyMap.put("Back", 2);
            frequencyMap.put("Chest", 2);
            frequencyMap.put("Arms", 1); // Smaller groups need less frequency
            frequencyMap.put("Core", 2); // Core can generally handle more frequency
        } else {
            // Adjust for fewer sessions
            frequencyMap.put("Legs", 1);
            frequencyMap.put("Back", 1);
            frequencyMap.put("Chest", 1);
            frequencyMap.put("Arms", 1);
            frequencyMap.put("Core", 1);
        }
        return frequencyMap;
    }

    public void adjustVolumeAndIntensityForMuscleGrowth(Exercise exercise, FitnessLevel fitnessLevel) {
        // Example adjustments, should be refined
        if (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE) {
            exercise.setSets(Math.min(exercise.getSets(), 3)); // Cap sets for beginners to avoid overtraining
            exercise.setReps(8); // Hypertrophy rep range
        } else {
            exercise.setSets(Math.min(exercise.getSets(), 4)); // Slightly higher volume for more experienced users
            exercise.setReps(10); // Within hypertrophy range
        }
    }


}
