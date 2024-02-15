package com.decadev.services;

import com.decadev.entities.User;
import com.decadev.entities.WorkoutPlan;
import com.decadev.repositories.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class WorkoutPlanService {
    @Autowired
    private  ExerciseService  exerciseService;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlan generateWorkoutPlan(User user) {
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
            workoutPlanRepository.createWorkoutPlan(user);
            return generateWorkoutPlan(user);
        }
    }


}
