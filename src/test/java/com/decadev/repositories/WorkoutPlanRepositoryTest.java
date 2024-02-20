package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.decadev.entities.WorkoutPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutPlanRepositoryTest {

    @Mock
    private DynamoDBMapper mapper;

    @InjectMocks
    private WorkoutPlanRepository workoutPlanRepository;

    private WorkoutPlan workoutPlan;

    @BeforeEach
    void setUp() {
        workoutPlan = new WorkoutPlan();
        workoutPlan.setPlanId("planId");
        workoutPlan.setUserId("userId");
        // Set up other necessary fields for workoutPlan
    }

    @Test
    void saveWorkoutPlan_Success() {
        workoutPlanRepository.save(workoutPlan);
        verify(mapper).save(workoutPlan);
    }

    @Test
    void findWorkoutPlanByUserId_Found() {

        PaginatedQueryList<WorkoutPlan> mockPaginatedList = mock(PaginatedQueryList.class);
        when(mockPaginatedList.isEmpty()).thenReturn(false);
        when(mockPaginatedList.get(0)).thenReturn(workoutPlan);


        when(mapper.query(eq(WorkoutPlan.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(mockPaginatedList);

        Optional<WorkoutPlan> result = workoutPlanRepository.findWorkoutPlanByUserId("userId");

        assertTrue(result.isPresent());
        assertEquals("userId", result.get().getUserId());
    }

    @Test
    void findWorkoutPlanByUserId_NotFound() {

        PaginatedQueryList<WorkoutPlan> mockPaginatedList = mock(PaginatedQueryList.class);
        when(mockPaginatedList.isEmpty()).thenReturn(true);
        when(mapper.query(eq(WorkoutPlan.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(mockPaginatedList);

        assertFalse(workoutPlanRepository.findWorkoutPlanByUserId("nonexistentUserId").isPresent());
    }

    @Test
    void updateWorkoutPlan_Success() {
        doNothing().when(mapper).save(eq(workoutPlan), any(DynamoDBSaveExpression.class));

        workoutPlanRepository.updateWorkoutPlan(workoutPlan);

        verify(mapper).save(eq(workoutPlan), any(DynamoDBSaveExpression.class));
    }

    @Test
    void deleteByUserId_FoundAndDeleted() {
        when(mapper.load(WorkoutPlan.class, "userId")).thenReturn(workoutPlan);
        doNothing().when(mapper).delete(workoutPlan);

        workoutPlanRepository.deleteByUserId("userId");

        verify(mapper).delete(workoutPlan);
    }
}