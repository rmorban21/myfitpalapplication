package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.decadev.entities.User;
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

    //finds a user's workout plan given their userId
    public Optional<WorkoutPlan> findWorkoutPlanByUserId(String userId) {
        WorkoutPlan workoutPlan = mapper.load(WorkoutPlan.class, userId);
        return Optional.ofNullable(workoutPlan);
    }

    //updates workout plan in response to updated user info: FitnessGoal, FitnessLevel, GymAccess and availability
    public WorkoutPlan updateWorkoutPlan(WorkoutPlan workoutPlan) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression
                .withExpectedEntry("planId",
                new ExpectedAttributeValue()
                        .withValue(new AttributeValue().withS(workoutPlan.getPlanId()))
                        .withExists(true));
        mapper.save(workoutPlan, saveExpression);
        return workoutPlan;
    }

    //deletes a workout plan with  a given userId
    public void deleteByUserId(String userId) {
        WorkoutPlan workoutPlan = mapper.load(WorkoutPlan.class, userId);
        if(workoutPlan != null) {
            mapper.delete(workoutPlan);
        }
    }

}

