package com.decadev.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@DynamoDBTable(tableName = "MyFitPalUsers")

public class User {
    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAutoGeneratedKey
    private String userId;

    @DynamoDBIndexHashKey(attributeName = "username", globalSecondaryIndexName = "usernameIndex")
    private String username;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute(attributeName = "gymAccess")
    private GymAccess gymAccess;

    // Fitness assessment attributes
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute(attributeName = "fitnessGoal")
    private FitnessGoal fitnessGoal;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute(attributeName = "fitnessLevel")
    private FitnessLevel fitnessLevel;

    @DynamoDBAttribute(attributeName = "availability")
    private int availability;

    //Explicit constructor for required fields
    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Default constructor for DynamoDb
    public User() {
    }

    //Custom builder method
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Static inner class for builder
    public static class UserBuilder {
        private String userId;
        private String username;
        private String password;
        private String email;
        private GymAccess gymAccess;
        private FitnessGoal fitnessGoal;
        private FitnessLevel fitnessLevel;
        private int availability;

        UserBuilder() {
        }

        // Builder methods for setting properties
        public UserBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder gymAccess(GymAccess gymAccess) {
            this.gymAccess = gymAccess;
            return this;
        }

        public UserBuilder fitnessGoal(FitnessGoal fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
            return this;
        }

        public UserBuilder fitnessLevel(FitnessLevel fitnessLevel) {
            this.fitnessLevel = fitnessLevel;
            return this;
        }

        public UserBuilder availability(int availability) {
            this.availability = availability;
            return this;
        }

        public User build() {
            User user = new User(userId, username, password, email);
            user.setGymAccess(gymAccess);
            user.setFitnessGoal(fitnessGoal);
            user.setFitnessLevel(fitnessLevel);
            user.setAvailability(availability);
            return user;
        }
    }
}
