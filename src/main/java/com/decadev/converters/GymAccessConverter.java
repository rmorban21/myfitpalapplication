package com.decadev.converters;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.decadev.entities.GymAccess;

public class GymAccessConverter implements DynamoDBTypeConverter<String, GymAccess> {
    @Override
    public String convert(final GymAccess gymAccess) {
        return gymAccess == null ? null : gymAccess.name();
    }

    @Override
    public GymAccess unconvert(final String s) {
        return s == null ? null : GymAccess.valueOf(s);
    }
}