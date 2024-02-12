package com.decadev.services;

import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) throws UserAlreadyExistsException, UserNotFoundException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        try {
            userRepository.findUserByUsername(user.getUsername());
            throw new UserAlreadyExistsException("Username already exists.");
        } catch (UserNotFoundException e) {
            userRepository.createUser(user);
        }
    }

    public User findUserById(String userId) {
        return userRepository.findUserById(userId);
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
 //   public void updateUserPreferences(String userId, Map<String, String> preferences) {
 //       userRepository.updateUserPreferences(userId, preferences);
 //   }
//
 //   public Map<String, String> getUserPreferences(String userId) {
 //       return userRepository.getUserPreferences(userId);
 //   }
 //   public void storeUserCredentials(String username, String password) throws UserAlreadyExistsException {
 //       User user = User.builder()
 //               .username(username)
 //               .build();
//
//
 //       String encodedPassword = passwordEncoder.encode(password);
 //       user.setPassword(encodedPassword);
//
 //       userRepository.createUser(user);
 //   }
}
