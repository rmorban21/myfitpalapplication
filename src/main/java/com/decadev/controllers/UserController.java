package com.decadev.controllers;

import com.decadev.entities.GymAccess;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.services.UserService;
import com.decadev.utils.EmailValidator;
import com.decadev.utils.PasswordValidator;
import com.decadev.utils.UsernameValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws UserAlreadyExistsException, UserNotFoundException {
        System.out.println("1");
        // Validate username using UsernameValidator
        if (!UsernameValidator.isValidUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username must be 6-12 characters long and contain only letters and numbers.");
        }
        System.out.println("2");

        // Validate email using EmailValidator
        if (!EmailValidator.isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }
        System.out.println("3");

        // Validate password using PasswordValidator
        if (!PasswordValidator.isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must consist of 6-15 characters and include uppercase, lowercase, numbers, and !$* symbols.");
        }
        System.out.println("4");

        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
}
