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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private DynamoDBMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    // TODO: Research DynamoDB's conditional write features to atomically check-and-create to prevent duplicates.
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
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression()
                .withExpected(Map.of("userId", new ExpectedAttributeValue(
                        new AttributeValue().withS(user.getUserId())
                )));

        mapper.save(user, saveExpression);
    }

    public void deleteUser(String userId) {
        User user = mapper.load(User.class, userId);
        mapper.delete(user);
        System.out.println("Successfully deleted user.");
    }
    public Optional<User> findUserById(String userId) {
        User user = mapper.load(User.class, userId);
        return Optional.ofNullable(user);
    }
    public boolean usernameExists(String username) {
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("usernameIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("username = :usernameVal")
                .withExpressionAttributeValues(Map.of(":usernameVal", new AttributeValue().withS(username)))
                .withLimit(1);

        List<User> users = mapper.query(User.class, queryExpression);
        return !users.isEmpty();
    }
}
