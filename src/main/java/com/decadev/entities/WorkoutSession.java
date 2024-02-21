package com.decadev.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.decadev.converters.ExerciseListConverter;
import lombok.Data;

import java.util.List;

@Data
@DynamoDBTable(tableName = "WorkoutSessions")
public class WorkoutSession {
    @DynamoDBHashKey(attributeName = "sessionId")
    @DynamoDBAutoGeneratedKey
    private String sessionId;

    @DynamoDBAttribute(attributeName = "userId") // Reference to parent plan
    private String userId;

    @DynamoDBAttribute(attributeName = "day")
    @DynamoDBTypeConvertedEnum
    private Day day;

    @DynamoDBTypeConverted(converter = ExerciseListConverter.class)
    @DynamoDBAttribute(attributeName = "exercises")
    private List<Exercise> exercises;

    // Constructor with DynamoDB annotations
    public WorkoutSession() {
    }

    public WorkoutSession (String sessionId, Day day, List<Exercise> exercises){}
}
