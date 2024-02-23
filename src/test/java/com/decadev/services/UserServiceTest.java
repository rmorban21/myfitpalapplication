package com.decadev.services;

import com.decadev.enums.FitnessGoal;
import com.decadev.enums.FitnessLevel;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId("1");
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");
    }

    @Test
    void createUser_Success() throws UserAlreadyExistsException, UserNotFoundException {
        when(userRepository.usernameExists(user.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        userService.createUser(user);

        verify(passwordEncoder).encode("password"); // Corrected to the original password
        verify(userRepository).createUser(any(User.class));
    }

    @Test
    void createUser_ThrowsUserAlreadyExistsException() {
        when(userRepository.usernameExists(user.getUsername())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(user));

    }

    @Test
    void findUserById_Success() throws UserNotFoundException {
        given(userRepository.findUserById(user.getUserId())).willReturn(Optional.of(user));

        User foundUser = userService.findUserById(user.getUserId());

        verify(userRepository).findUserById(user.getUserId());
        assertEquals(user.getUserId(), foundUser.getUserId());
    }

    @Test
    void findUserById_ThrowsUserNotFoundException() {
        given(userRepository.findUserById("nonexistent")).willReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.findUserById("nonexistent");
        });
    }

    @Test
    void updateUser_UpdatesPasswordWhenPresent() {
        // Setup
        User user = new User("1", "testUser", "password", "test@example.com");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        // Execute
        userService.updateUser(user);

        // Verify
        verify(passwordEncoder).encode("password");
        verify(userRepository).updateUser(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void deleteUser_Success() {
        // Setup
        String userId = "1";

        // Execute
        userService.deleteUser(userId);

        // Verify
        verify(userRepository).deleteUser(userId);
    }

    @Test
    void updateFitnessLevel_Success() throws UserNotFoundException {
        // Setup
        String userId = "1";
        FitnessLevel fitnessLevel = FitnessLevel.ADVANCED;
        User user = new User(userId, "testUser", "password", "test@example.com");
        user.setFitnessLevel(FitnessLevel.BEGINNER);
        when(userRepository.findUserById(userId)).thenReturn(Optional.of(user));

        // Execute
        userService.updateFitnessLevel(userId, fitnessLevel);

        // Verify
        assertEquals(fitnessLevel, user.getFitnessLevel());
        verify(userRepository).updateUser(user);
    }

    @Test
    void updateFitnessGoal_Success() throws UserNotFoundException {
        // Setup
        String userId = "1";
        FitnessGoal fitnessGoal = FitnessGoal.BUILD_MUSCLE;
        User user = new User(userId, "testUser", "password", "test@example.com");
        user.setFitnessGoal(FitnessGoal.WEIGHT_LOSS);
        when(userRepository.findUserById(userId)).thenReturn(Optional.of(user));

        // Execute
        userService.updateFitnessGoal(userId, fitnessGoal);

        // Verify
        assertEquals(fitnessGoal, user.getFitnessGoal());
        verify(userRepository).updateUser(user);
    }

    @Test
    void updateFitnessGoal_UserNotFound() {
        // Setup
        String userId = "non-existent";
        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(UserNotFoundException.class, () -> userService.updateFitnessGoal(userId, FitnessGoal.STRENGTH));
    }

    @Test
    void updateFitnessLevel_UserNotFound() {
        // Setup
        String userId = "non-existent";
        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(UserNotFoundException.class, () -> userService.updateFitnessLevel(userId, FitnessLevel.ADVANCED));
    }
}