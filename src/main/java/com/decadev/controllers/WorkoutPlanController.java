//package com.decadev.controllers;
//
//import com.decadev.entities.User;
//import com.decadev.entities.WorkoutPlan;
//import com.decadev.services.WorkoutPlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
////TODO: Consider security- authentication and authorixation to protect endpoints
//// (ie: users should only be able to access their own workoutplans not others')
//@RestController
//@RequestMapping("/api/workout-plans")
//public class WorkoutPlanController {
//    @Autowired
//    private WorkoutPlanService workoutPlanService;
//
//    // Create a new workout plan for the authenticated user
//    @PostMapping
//    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@RequestBody User user) {
//        WorkoutPlan workoutPlan = workoutPlanService.generateAndSaveWorkoutPlan(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(workoutPlan);
//    }
//
//    // Retrieve the current workout plan for the authenticated user
//    @GetMapping("/{userId}")
//    public ResponseEntity<WorkoutPlan> getWorkoutPlan(@PathVariable String userId) {
//        return workoutPlanService.findWorkoutPlanByUserId(userId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}