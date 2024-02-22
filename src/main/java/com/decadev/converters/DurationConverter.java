package com.decadev.converters;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Duration;

public class DurationConverter implements DynamoDBTypeConverter<String, Duration> {

    @Override
    public String convert(Duration duration) {
        return duration.toString();
    }

    @Override
    public Duration unconvert(String stringValue) {
        return Duration.parse(stringValue);
    }
}
