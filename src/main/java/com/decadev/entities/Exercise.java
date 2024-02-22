package com.decadev.entities;

import com.decadev.enums.ExerciseType;
import com.decadev.enums.FitnessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class Exercise {
    private String name;
    private FitnessLevel fitnessLevel;
    private ExerciseType exerciseType;
    private String bodyPart;
    private String equipment;
    private Integer sets;
    private Integer reps;
    private Duration duration;

    // Private constructor to prevent direct instantiation
    public Exercise() {}

    // Builder method to create Exercise objects
    public static ExerciseBuilder builder() {
        return new ExerciseBuilder();
    }

    //TODO: Consider adding validation in the builder's build method to ensure that
    // all required fields are set and valid.

    // ExerciseBuilder class to construct Exercise objects
    public static class ExerciseBuilder {
        private final Exercise exercise;

        public ExerciseBuilder() {
            exercise = new Exercise();
        }

        public ExerciseBuilder name(String name) {
            exercise.name = name;
            return this;
        }

        public ExerciseBuilder fitnessLevel(FitnessLevel fitnessLevel) {
            exercise.fitnessLevel = fitnessLevel;
            return this;
        }

        public ExerciseBuilder exerciseType(ExerciseType exerciseType) {
            exercise.exerciseType = exerciseType;
            return this;
        }

        public ExerciseBuilder bodyPart(String bodyPart) {
            exercise.bodyPart = bodyPart;
            return this;
        }

        public ExerciseBuilder equipment(String equipment) {
            exercise.equipment = equipment;
            return this;
        }

        public ExerciseBuilder sets(Integer sets) {
            exercise.sets = sets;
            return this;
        }

        public ExerciseBuilder reps(Integer reps) {
            exercise.reps = reps;
            return this;
        }

        public ExerciseBuilder duration(Duration duration) {
            exercise.duration = duration;
            return this;
        }

        public Exercise build() {
            // Perform validation if necessary
            return exercise;
        }
    }

}
