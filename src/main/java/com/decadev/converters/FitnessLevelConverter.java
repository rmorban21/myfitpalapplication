package com.decadev.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.decadev.enums.FitnessLevel;
import org.springframework.stereotype.Component;

@Component
public class FitnessLevelConverter implements DynamoDBTypeConverter<String, FitnessLevel> {

    @Override
    public String convert(final FitnessLevel fitnessLevel) {
        return fitnessLevel == null ? null : fitnessLevel.name();
    }

    @Override
    public FitnessLevel unconvert(final String s) {
        return FitnessLevel.valueOf(s);
    }
}