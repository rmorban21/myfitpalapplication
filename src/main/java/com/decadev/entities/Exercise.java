package com.decadev.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class Exercise {
    private String name;
    private FitnessLevel fitnessLevel;
    private String exerciseType;
    private String equipment;
    private Integer sets;
    private Integer reps;
    private Duration duration;

}
