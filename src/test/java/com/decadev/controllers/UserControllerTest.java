package com.decadev.controllers;

import com.decadev.entities.GymAccess;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import com.decadev.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void createUser_Success() throws Exception {
        User user = new User("1", "testUser", "password", "test@example.com");

        doNothing().when(userService).createUser(any(User.class));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully."));
    }

    @Test
    void createUser_UserAlreadyExistsException() throws Exception {
        User user = new User("1", "testUser", "password", "test@example.com");

        doThrow(new UserAlreadyExistsException("Username already exists.")).when(userService).createUser(any(User.class));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists."));
    }

    @Test
    void getUserById_NotFound() throws Exception {
        given(userService.findUserById("1")).willThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }

    @Test
    void updateUser_Success() throws Exception {
        User user = new User("1", "testUser", "password", "test@example.com");

        doNothing().when(userService).updateUser(any(User.class));

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User updated successfully"));
    }

    @Test
    void deleteUser_Success() throws Exception {
        String userId = "1";

        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }

    @Test
    void updateGymAccess_Success() throws Exception {
        String userId = "1";
        GymAccess gymAccess = GymAccess.FULL_GYM_ACCESS;

        doNothing().when(userService).updateGymAccess(eq(userId), any(GymAccess.class));

        mockMvc.perform(put("/api/users/{userId}/gymAccess", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gymAccess)))
                .andExpect(status().isOk())
                .andExpect(content().string("Gym access updated successfully"));
    }

    @Test
    void updateGymAccess_UserNotFound() throws Exception {
        String userId = "nonexistent";
        GymAccess gymAccess = GymAccess.HOME_GYM_WITH_WEIGHTS;

        doThrow(new UserNotFoundException("User not found")).when(userService).updateGymAccess(eq(userId), any(GymAccess.class));

        mockMvc.perform(put("/api/users/{userId}/gymAccess", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gymAccess)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }


}
