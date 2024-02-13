package com.decadev.entities;

import lombok.Data;

import java.time.Duration;

@Data
public class Exercise {
    private String name;
    private String exerciseType;
    private Integer sets;
    private Integer reps;
    private Duration duration;

}
