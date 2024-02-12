package com.decadev.controllers;

import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.services.UserService;
import com.decadev.utils.EmailValidator;
import com.decadev.utils.PasswordValidator;
import com.decadev.utils.UsernameValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
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

//    @PutMapping("/{userId}/preferences")
//    public ResponseEntity<String> updateUserPreferences(@PathVariable String userId,
//                                                        @RequestBody Map<String, String> preferences) {
//        userService.updateUserPreferences(userId, preferences);
//        return ResponseEntity.ok("User preferences updated successfully");
//    }
}
