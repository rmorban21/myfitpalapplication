package com.decadev.services;

import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import com.decadev.enums.GymAccess;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO: Ensure that all user inputs are validated before processing to prevent injection attacks or storing invalid data.
    public void createUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (userRepository.usernameExists(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists.");
        }

        log.info("Creating a new user with username: {}", user.getUsername());
        userRepository.createUser(user);
    }

    public User findUserById(String userId) throws UserNotFoundException {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    //TODO: Add detailed validation for all fields that can be updated.
    public void updateUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.updateUser(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }

    // TODO: Consider implementing a general approach to handling partial updates to reduce redundancy for below methods
    public void updateGymAccess(String userId, GymAccess gymAccess) throws UserNotFoundException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        user.setGymAccess(gymAccess);
        userRepository.updateUser(user);
    }

    public void updateFitnessLevel(String userId, FitnessLevel fitnessLevel) throws UserNotFoundException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        user.setFitnessLevel(fitnessLevel);
        userRepository.updateUser(user); // This calls mapper.save(user) internally
    }

    public void updateFitnessGoal(String userId, FitnessGoal fitnessGoal) throws UserNotFoundException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        user.setFitnessGoal(fitnessGoal);
        userRepository.updateUser(user); // Uses the same save method for updates
    }


}
