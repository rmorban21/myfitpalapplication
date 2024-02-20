package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.repositories.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
        workoutPlan.setGeneralAdvice(generateGeneralAdvice());

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
        List<Exercise> filteredExercises = exerciseService.filterExercisesByGoal(exerciseService.getCustomizedExercises(user), user.getFitnessGoal());
        int sessionsPerWeek = exerciseService.calculateSessionsPerWeek(user.getAvailability(), user.getFitnessGoal());
        Map<String, Integer> frequencyMap = calculateFrequencyMap(sessionsPerWeek, user.getFitnessGoal());

        // Allocate exercises based on frequency map, ensuring balanced workout plans
        List<Exercise> planExercises = new ArrayList<>();
        frequencyMap.forEach((bodyPart, frequency) -> {
            List<Exercise> bodyPartExercises = filteredExercises.stream()
                    .filter(exercise -> exercise.getBodyPart().equalsIgnoreCase(bodyPart))
                    .limit(frequency)
                    .collect(Collectors.toList());
            planExercises.addAll(bodyPartExercises);
        });

        return planExercises;
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
        // Make sure to pass both sessionsPerWeek and the specific FitnessGoal to calculateFrequencyMap
        Map<String, Integer> frequencyMap = calculateFrequencyMap(sessionsPerWeek, user.getFitnessGoal());

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
        Optional<WorkoutPlan> existingPlanOpt = workoutPlanRepository.findWorkoutPlanByUserId(user.getUserId());
        if (existingPlanOpt.isPresent()) {
            WorkoutPlan existingPlan = existingPlanOpt.get();
            WorkoutPlan updatedPlan = generateAndSaveWorkoutPlan(user);
            updatedPlan.setPlanId(existingPlan.getPlanId()); // Keep the same ID for the update
            return workoutPlanRepository.updateWorkoutPlan(updatedPlan);
        } else {
            // Generate a new plan if not found
            return generateAndSaveWorkoutPlan(user);
        }
    }

    private Map<String, Integer> calculateFrequencyMap(int sessionsPerWeek, FitnessGoal goal) {
        // Initial map with minimal frequency for each body part
        Map<String, Integer> frequencyMap = new HashMap<>() {{
            put("Legs", 1);
            put("Back", 1);
            put("Chest", 1);
            put("Arms", 1);
            put("Core", 1);
            put("Shoulders", 1); // Ensure shoulders are included for a balanced approach
        }};

        if (goal == FitnessGoal.BUILD_MUSCLE && sessionsPerWeek >= 5) {
            // Increase frequency for larger muscle groups and shoulders for muscle building
            frequencyMap.put("Legs", 2);
            frequencyMap.put("Back", 2);
            frequencyMap.put("Chest", 2);
            frequencyMap.put("Shoulders", 2); // Important for a balanced upper body strength and aesthetics
        } else if (goal == FitnessGoal.STRENGTH && sessionsPerWeek >= 4) {
            // Strength plans may focus more on compound movements, affecting frequency distribution
            frequencyMap.put("Legs", 2);
            frequencyMap.put("Back", 2);
            frequencyMap.put("Shoulders", 2); // Essential for strength, especially overhead pressing strength
        }

        // Adjust core frequency to be daily if possible, as core can generally handle more frequency
        frequencyMap.put("Core", Math.min(7, sessionsPerWeek));

        return frequencyMap;
    }

    public void adjustVolumeAndIntensityForMuscleGrowth(Exercise exercise, FitnessLevel fitnessLevel) {
        // Adjustments based on the provided fitness level
        if (fitnessLevel == FitnessLevel.BEGINNER || fitnessLevel == FitnessLevel.INTERMEDIATE) {
            exercise.setSets(Math.min(exercise.getSets(), 3)); // Cap sets for beginners
            exercise.setReps(8); // Target hypertrophy rep range
        } else {
            exercise.setSets(Math.min(exercise.getSets(), 4)); // Higher volume for advanced users
            exercise.setReps(10); // Still within hypertrophy range
        }
    }
    public Optional<WorkoutPlan> findWorkoutPlanByUserId(String userId) {
        return workoutPlanRepository.findWorkoutPlanByUserId(userId);
    }


}
