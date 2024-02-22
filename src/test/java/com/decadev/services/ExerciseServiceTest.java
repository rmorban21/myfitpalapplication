import com.decadev.entities.*;
import com.decadev.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExerciseServiceTest {

    @InjectMocks
    private ExerciseService exerciseService;

    @Mock
    private Exercise exerciseMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        exerciseService.init(); // Initialize exercises
    }

    @Test
    public void testIsEquipmentAccessible() {
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_NO_WEIGHTS, "None"));
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_WITH_WEIGHTS, "None"));
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_WITH_WEIGHTS, "Dumbbells"));
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_WITH_WEIGHTS, "Foam Roller"));
        assertTrue(exerciseService.isEquipmentAccessible(GymAccess.FULL_GYM_ACCESS, "Any Equipment"));
        assertFalse(exerciseService.isEquipmentAccessible(GymAccess.HOME_GYM_NO_WEIGHTS, "Dumbbells"));
    }

    @Test
    public void testCustomizeExerciseIntensity() {
        Exercise exercise1 = new Exercise("Test Cardio Exercise", FitnessLevel.BEGINNER, ExerciseType.CARDIO, "Legs", "None", null, null, Duration.ofMinutes(10));
        Exercise exercise2 = new Exercise("Test Shoulder Exercise", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Shoulders", "Dumbbells", 3, 10, null);
        Exercise exercise3 = new Exercise("Test Chest Exercise", FitnessLevel.INTERMEDIATE, ExerciseType.COMPOUND, "Chest", "Bench Press", 3, 10, null);


    }

    @Test
    public void testGetAllExercises() {
        List<Exercise> allExercises = exerciseService.getAllExercises();
        assertEquals(45, allExercises.size()); // Assuming 45 exercises are initialized in the ExerciseService.init() method
    }
}