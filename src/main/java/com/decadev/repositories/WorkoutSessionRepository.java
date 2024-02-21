package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.decadev.entities.WorkoutSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkoutSessionRepository {



    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public WorkoutSessionRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Optional<WorkoutSession> findById(String sessionId) {
        return Optional.ofNullable(dynamoDBMapper.load(WorkoutSession.class, sessionId));
    }

    public WorkoutSession save(WorkoutSession workoutSession) {
        dynamoDBMapper.save(workoutSession);
        return workoutSession;
    }

    public void deleteById(String sessionId) {
        WorkoutSession workoutSession = findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Workout session not found"));
        dynamoDBMapper.delete(workoutSession);
    }

}
