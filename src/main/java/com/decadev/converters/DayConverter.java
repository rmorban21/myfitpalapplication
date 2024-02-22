package com.decadev.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.decadev.enums.Day;
import com.decadev.enums.FitnessGoal;
import org.springframework.stereotype.Component;

@Component
public class DayConverter implements DynamoDBTypeConverter<String, Day> {
    @Override
    public String convert(final Day day) {
        return day == null ? null : day.name();
    }

    @Override
    public Day unconvert(final String s) {
        return Day.valueOf(s);
    }
}
