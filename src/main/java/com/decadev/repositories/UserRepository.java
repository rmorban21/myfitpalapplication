package com.decadev.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.decadev.entities.User;
import com.decadev.exceptions.UserAlreadyExistsException;
import com.decadev.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private DynamoDBMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private static final String TABLE_NAME = "MyFitPalUsers";

    public void createUser(User user) throws UserAlreadyExistsException {
        try {
            if (findUserByUsername(user.getUsername()) != null) {
                throw new UserAlreadyExistsException("Username already exists");
            }
        } catch (UserNotFoundException e) {
            // Username does not exist, proceed to create user
        }

        user.setUserId(UUID.randomUUID().toString());
        mapper.save(user);
    }
    public User findUserByUsername(String username) throws UserNotFoundException {
        // Create a query expression with the hash key condition
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("usernameIndex") // Ensure this matches your DynamoDB GSI name
                .withConsistentRead(false) // Use eventual consistency for GSI queries
                .withHashKeyValues(User.builder().username(username).build()); // Set the hash key value to the username

        // Query the table using the mapper
        List<User> users = mapper.query(User.class, queryExpression);

        // Check if the query returned any results
        if (users.isEmpty()) {
            throw new UserNotFoundException("No User found with the username: " + username);
        }

        // Return the first user in the list
        return users.get(0);
    }
    public void updateUser(User user) {
        User userToUpdate = user.builder()
                .userId(user.getUserId())
                .availability(user.getAvailability())
                .email(user.getEmail())
                .fitnessGoal(user.getFitnessGoal())
                .fitnessLevel(user.getFitnessLevel())
                .build();
        mapper.save(userToUpdate, new DynamoDBSaveExpression()
                .withExpectedEntry("userId", new ExpectedAttributeValue(
                        new AttributeValue().withS(user.getUserId())
                )));
    }
    public void deleteUser(String userId) {
        User user = mapper.load(User.class, userId);
        mapper.delete(user);
        System.out.println("Successfully deleted user.");
    }
    public User findUserById(String userId) {
        return mapper.load(User.class, userId);
    }
 //   public void updateUserPreferences(String userId, Map<String, String> preferences) {
 //       User user = findUserById(userId);
 //       if (user != null) {
 //           user.setPreferences(preferences);
 //           updateUser(user);
 //       }
 //   }
 //   public Map<String, String> getUserPreferences(String userId) {
 //       User user = findUserById(userId);
 //       return (user != null) ? user.getPreferences() : Collections.emptyMap();
 //   }

}