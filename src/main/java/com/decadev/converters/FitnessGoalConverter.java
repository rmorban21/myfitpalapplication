package com.decadev.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.decadev.enums.FitnessGoal;
import org.springframework.stereotype.Component;

@Component
public class FitnessGoalConverter implements DynamoDBTypeConverter<String, FitnessGoal> {

    @Override
    public String convert(final FitnessGoal fitnessGoal) {
        return fitnessGoal == null ? null : fitnessGoal.name();
    }

    @Override
    public FitnessGoal unconvert(final String s) {
        return FitnessGoal.valueOf(s);
    }
}