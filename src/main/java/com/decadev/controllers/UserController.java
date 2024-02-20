package com.decadev.controllers;

import com.decadev.entities.FitnessGoal;
import com.decadev.entities.FitnessLevel;
import com.decadev.entities.GymAccess;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.services.UserService;
import com.decadev.utils.EmailValidator;
import com.decadev.utils.PasswordValidator;
import com.decadev.utils.UsernameValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            if (!UsernameValidator.isValidUsername(user.getUsername()) || !EmailValidator.isValidEmail(user.getEmail()) || !PasswordValidator.isValidPassword(user.getPassword())) {
                log.warn("Validation failed for user: {}", user);
                return ResponseEntity.badRequest().body("Validation error. Check username, email, and password.");
            }
            userService.createUser(user);
            log.info("Created a new user with username: {}", user.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        } catch (UserAlreadyExistsException | UserNotFoundException e) {
            log.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error creating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            log.error("User not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error retrieving user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PutMapping("/{userId}/gymAccess")
    public ResponseEntity<String> updateGymAccess(@PathVariable String userId, @RequestBody GymAccess gymAccess) {
        try {
            userService.updateGymAccess(userId, gymAccess);
            return ResponseEntity.ok("Gym access updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating gym access");
        }
    }

    @PutMapping("/{userId}/fitnessLevel")
    public ResponseEntity<String> updateFitnessLevel(@PathVariable String userId, @RequestBody FitnessLevel fitnessLevel) {
        try {
            userService.updateFitnessLevel(userId, fitnessLevel);
            return ResponseEntity.ok("Fitness level updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating fitness level for user {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating fitness level");
        }
    }

    @PutMapping("/{userId}/fitnessGoal")
    public ResponseEntity<String> updateFitnessGoal(@PathVariable String userId, @RequestBody FitnessGoal fitnessGoal) {
        try {
            userService.updateFitnessGoal(userId, fitnessGoal);
            return ResponseEntity.ok("Fitness goal updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating fitness goal for user {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating fitness goal");
        }
    }

}
