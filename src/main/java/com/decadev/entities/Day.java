package com.decadev.entities;


public enum Day {
    // First 4 are for build_muscle and strength goals - build muscle will have isolation exercises while strength will consist of only compound movements and possibly exclude arms
    // Second set is for weight loss goal
    BACK_AND_SHOULDERS("Back and Shoulders"),
    LEGS("Legs"),
    CHEST("Chest"),
    LEGS_ARMS("Legs and Arms"),
    UPPER_BODY("Upper Body"),
    LOWER_BODY("Lower Body");

    private final String day;

    Day(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return this.day;
    }
}