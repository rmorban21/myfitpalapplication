package com.decadev.services;

import com.decadev.entities.Exercise;
import com.decadev.entities.User;
import com.decadev.entities.WorkoutPlan;
import com.decadev.repositories.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WorkoutPlanService {
    @Autowired
    private  ExerciseService  exerciseService;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlan generateWorkoutPlan(User user) {
        List<Exercise> exercises = exerciseService.getCustomizedExercises(user);
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setUserId(user.getUserId());
        workoutPlan.setExercises(exercises);
        return new WorkoutPlan();
    }

    public WorkoutPlan updateUserWorkoutPlan(User user) {
        Optional<WorkoutPlan> existingPlan =  workoutPlanRepository.findWorkoutPlanByUserId(user.getUserId());
        if (existingPlan.isPresent()) {
            WorkoutPlan updatedPlan = generateWorkoutPlan(user);
            updatedPlan.setPlanId(existingPlan.get().getPlanId());
            workoutPlanRepository.updateWorkoutPlan(updatedPlan);
            return updatedPlan;
        }  else {
            workoutPlanRepository.save(WorkoutPlan workoutPlan);
            return generateWorkoutPlan(user);
        }
    }


}
