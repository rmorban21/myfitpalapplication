package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.decadev.entities.WorkoutPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
        // secondary index on 'userId' for direct queries
        DynamoDBQueryExpression<WorkoutPlan> queryExpression = new DynamoDBQueryExpression<WorkoutPlan>()
                .withIndexName("userId-index") // Use the correct index name as set up in DynamoDB
                .withConsistentRead(false)
                .withKeyConditionExpression("userId = :val1")
                .withExpressionAttributeValues(Map.of(":val1", new AttributeValue().withS(userId)));

        List<WorkoutPlan> result = mapper.query(WorkoutPlan.class, queryExpression);
        // Since each user is expected to have only one workout plan, directly return the first result if present
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
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

