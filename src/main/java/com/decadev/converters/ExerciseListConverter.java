package com.decadev.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.decadev.entities.Exercise;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
//TODO: Consider logging the exception details to aid in debugging
// any potential issues with the conversion process.
public class ExerciseListConverter implements DynamoDBTypeConverter<String, List<Exercise>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convert(List<Exercise> exercises) {
        try {
            return objectMapper.writeValueAsString(exercises);
        } catch (Exception e) {
            throw new RuntimeException("Error converting list of exercises to JSON string", e);
        }
    }

    @Override
    public List<Exercise> unconvert(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Exercise>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to list of exercises", e);
        }
    }
}
