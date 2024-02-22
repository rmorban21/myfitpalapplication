package com.decadev.enums;

public enum ExerciseType {
    CARDIO("Cardio: Exercises intended to increase heart rate and stamina. Includes running, cycling, and aerobics."),
    COMPOUND("Compound: Multi-joint movements that work several muscles or muscle groups at one time. Ideal for overall strength and conditioning."),
    ISOLATION("Isolation: Exercises that target a specific muscle or muscle group. Excellent for focused strength training.");

    private final String description;

    ExerciseType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}