package com.decadev.dataTransferObjects;

import com.decadev.entities.Exercise;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseDayResponse {
    private List<Exercise> coreExercises;
    private List<Exercise> cardioExercises;
    private List<Exercise> dayExercises;
}
