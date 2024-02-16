package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.decadev.entities.WorkoutPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WorkoutPlanRepository {
    @Autowired
    private DynamoDBMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(WorkoutPlanRepository.class);

    //saves workout plan to user account
    public WorkoutPlan save(WorkoutPlan workoutPlan) {
        mapper.save(workoutPlan);
        return workoutPlan;
    }

    public Optional<WorkoutPlan> findWorkoutPlanByUserId(String userId) {
        WorkoutPlan workoutPlan = mapper.load(WorkoutPlan.class, userId);
        return Optional.ofNullable(workoutPlan);
    }

    public WorkoutPlan updateWorkoutPlan(WorkoutPlan workoutPlan) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression.withExpectedEntry("planId",
                new ExpectedAttributeValue(new AttributeValue(workoutPlan.getPlanId())).withExists(true));
        mapper.save(workoutPlan, saveExpression);
        return workoutPlan;
    }

    public void deleteByUserId(String userId) {
        WorkoutPlan workoutPlan = mapper.load(WorkoutPlan.class, userId);
        if (workoutPlan != null) {
            mapper.delete(workoutPlan);
        }
    }
}

