package com.decadev.services;

import com.decadev.entities.GymAccess;
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
    public void updateGymAccess(String userId, GymAccess gymAccess) throws UserNotFoundException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        user.setGymAccess(gymAccess);
        userRepository.updateUser(user);
    }
}
