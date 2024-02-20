package com.decadev.controllers;

import com.decadev.entities.User;
import com.decadev.entities.WorkoutPlan;
import com.decadev.services.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    // Endpoint to generate and save a new workout plan for a user
    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@RequestBody User user) {
        WorkoutPlan workoutPlan = workoutPlanService.generateAndSaveWorkoutPlan(user);
        return ResponseEntity.ok(workoutPlan);
    }

    // Endpoint to retrieve the current workout plan for a user
    @GetMapping("/{userId}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlan(@PathVariable String userId) {
        return workoutPlanService.findWorkoutPlanByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update an existing workout plan for a user
    @PutMapping("/{userId}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId); // Ensure the user object has the correct userId set from the path variable
        WorkoutPlan updatedWorkoutPlan = workoutPlanService.updateUserWorkoutPlan(user);
        return ResponseEntity.ok(updatedWorkoutPlan);
    }
}