package com.decadev.services;

import com.decadev.entities.*;
import com.decadev.repositories.WorkoutSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkoutSessionService {
    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private ExerciseService exerciseService;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository, ExerciseService exerciseService) {
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseService = exerciseService;
    }

    public WorkoutSession generateWorkoutSession(User user) {
        // Determine the workout day based on the user's fitness goal
        Day day = determineWorkoutDay(user.getFitnessGoal());

        // Get exercises for the workout session based on user and fitness goal
        List<Exercise> sessionExercises = getExercisesForWorkoutSession(user);

        // Create the workout session
        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setUserId(user.getUserId()); // Assuming user.getUserId() represents the planId
        workoutSession.setDay(day);
        workoutSession.setExercises(sessionExercises);

        // Save the workout session to the repository
        return workoutSessionRepository.save(workoutSession);
    }

    private List<Exercise> getExercisesForWorkoutSession(User user) {
        // Filter exercises based on user's fitness goal, gym access, and fitness level
        List<Exercise> filteredExercises = getCustomizedExercises(user);

        // Further filter exercises based on the workout day
        return filterExercisesByDay(filteredExercises, determineWorkoutDay(user.getFitnessGoal()), user.getGymAccess());
    }

    public List<Exercise> filterExercisesByDay(List<Exercise> exercises, Day day, GymAccess gymAccess) {
        // Logic to filter exercises based on the workout day
        switch (day) {
            case BACK_AND_SHOULDERS:
                // Filter exercises for back and shoulders
                return filterExercisesByBodyPart(Arrays.asList("Back", "Shoulders"), exercises, gymAccess);
            case LEGS:
                // Filter exercises for legs
                return filterExercisesByBodyPart(Collections.singletonList("Legs"), exercises, gymAccess);
            case CHEST:
                // Filter exercises for chest
                return filterExercisesByBodyPart(Collections.singletonList("Chest"), exercises, gymAccess);
            case LEGS_ARMS:
                // Filter exercises for legs and arms
                return filterExercisesByBodyPart(Arrays.asList("Legs", "Arms"), exercises, gymAccess);
            case UPPER_BODY:
                // Filter exercises for upper body (back, chest, shoulders)
                return filterExercisesByBodyPart(Arrays.asList("Back", "Chest", "Shoulders"), exercises, gymAccess);
            case LOWER_BODY:
                // Filter exercises for lower body (legs)
                return filterExercisesByBodyPart(Collections.singletonList("Legs"), exercises, gymAccess);
            default:
                // Return all exercises if no specific day is defined
                return exercises;
        }
    }

    public List<Exercise> filterExercisesByBodyPart(List<String> bodyParts, List<Exercise> exercises, GymAccess gymAccess) {
        return exercises.stream()
                .filter(e -> bodyParts.contains(e.getBodyPart()))
                .filter(e -> exerciseService.isEquipmentAccessible(gymAccess, e.getEquipment()))
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

    public List<Exercise> filterExercisesByBodyPartForStrength(List<String> bodyParts, List<Exercise> exercises, GymAccess gymAccess) {
        return exercises.stream()
                .filter(e -> bodyParts.contains(e.getBodyPart()))
                .filter(e -> exerciseService.isEquipmentAccessible(gymAccess, e.getEquipment()))
                .collect(Collectors.toList());
    }

    //generate focused sessions for limited availability
    public List<List<Exercise>> generateFocusedSessions(User user) {
        // Check if the user's availability is below the minimum required for a comprehensive plan
        if (user.getAvailability() < 2) {
            // TODO: Ideally, this check should be performed before calling this method
            throw new IllegalArgumentException("Availability is below the minimum required threshold.");
        }

        List<Exercise> customizedExercises = getCustomizedExercises(user);
        // Assuming the user's fitness goal has already been considered in getCustomizedExercises

        // Example focuses based on common fitness goals
        List<String> focusAreas = determineFocusAreasBasedOnGoal(user.getFitnessGoal());

        Map<String, List<Exercise>> exercisesByFocus = new HashMap<>();
        for (String focus : focusAreas) {
            exercisesByFocus.put(focus, new ArrayList<>());
        }

        // Distribute exercises into the focus areas
        for (Exercise exercise : customizedExercises) {
            for (String focus : focusAreas) {
                if (exercise.getBodyPart().equalsIgnoreCase(focus)) {
                    exercisesByFocus.get(focus).add(exercise);
                }
            }
        }

        // Organize exercises into sessions
        List<List<Exercise>> focusedSessions = new ArrayList<>();
        for (String focus : focusAreas) {
            List<Exercise> focusExercises = exercisesByFocus.get(focus);
            if (!focusExercises.isEmpty()) {
                // Here you could further split or organize exercises based on total session time, intensity, etc.
                focusedSessions.add(focusExercises);
            }
        }

        return focusedSessions;
    }
    private List<String> determineFocusAreasBasedOnGoal(FitnessGoal goal) {
        // return a list of focus areas (body parts or exercise types) based on the user's fitness goal
        switch (goal) {
            case WEIGHT_LOSS:
                return Arrays.asList("Cardio", "Upper Body", "Lower Body");
            case BUILD_MUSCLE:
                return Arrays.asList("Legs", "Back", "Chest", "Arms", "Shoulders");
            case STRENGTH:
                return Arrays.asList("Legs", "Back", "Chest", "Shoulders");
            default:
                return Collections.emptyList();
        }
    }
    public List<Exercise> getCustomizedExercises(User user) {
        List<Exercise> accessibleExercises = filterExercisesByGymAccess(user.getGymAccess());
        return filterExercisesByFitnessLevel(accessibleExercises, user.getFitnessLevel()).stream()
                .map(exercise -> exerciseService.customizeExerciseIntensity(exercise, user.getFitnessLevel(), user.getFitnessGoal())) // Pass the required parameters
                .collect(Collectors.toList());
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
    public List<Exercise> filterExercisesByGymAccess(GymAccess gymAccess) {
        if (gymAccess == null) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }

        List<Exercise> exercises = exerciseService.getAllExercises(); // Obtain the list of exercises from ExerciseService
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

    private Day determineWorkoutDay(FitnessGoal fitnessGoal) {
        // Logic to determine the workout day based on fitness goal
        switch (fitnessGoal) {
            case BUILD_MUSCLE:
            case STRENGTH:
                // For build muscle and strength goals
                return determineBuildMuscleAndStrengthDay();
            case WEIGHT_LOSS:
                // For weight loss goal
                return determineWeightLossDay();
            default:
                // Default to a generic workout day
                return Day.UPPER_BODY;
        }
    }

    private Day determineBuildMuscleAndStrengthDay() {
        // Logic to determine the workout day for build muscle and strength goals
        // For example, you could rotate through different muscle groups
        // You can implement your custom logic here
        // For now, let's return a generic upper body day
        return Day.UPPER_BODY;
    }

    private Day determineWeightLossDay() {
        // Logic to determine the workout day for weight loss goals
        // You can implement your custom logic here
        // For now, let's return a generic lower body day
        return Day.LOWER_BODY;
    }
    public WorkoutSession createWorkoutSession(User user, String planId, Day day) {
        List<List<Exercise>> focusedSessions = generateFocusedSessions(user);
        // For simplicity, assuming the first session is used
        List<Exercise> sessionExercises = focusedSessions.isEmpty() ? List.of() : focusedSessions.get(0);
        WorkoutSession workoutSession = new WorkoutSession(planId, day, sessionExercises);
        return workoutSessionRepository.save(workoutSession);
    }

    public Optional<WorkoutSession> findWorkoutSessionById(String sessionId) {
        return workoutSessionRepository.findById(sessionId);
    }

    public void deleteWorkoutSessionById(String sessionId) {
        workoutSessionRepository.deleteById(sessionId);
    }
}