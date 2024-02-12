package com.decadev.entities;

public enum FitnessGoal {
    STRENGTH("Strength"),
    WEIGHT_LOSS("Weight Loss"),
    BUILD_MUSCLE("Build Muscle");

    private final String goal;

    FitnessGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return this.goal;
    }
}
