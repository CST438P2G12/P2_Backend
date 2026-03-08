package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Exercise;
import g12.p2.cst438.g12_springboot.entities.User;
import g12.p2.cst438.g12_springboot.entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExerciseControllerTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseController exerciseController;

    private User user;
    private Workout workout;
    private Exercise exercise;
    private List<Exercise> exercisesList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Test User");
        workout = new Workout(user, LocalDate.of(1900, 1, 1));
        exercise = new Exercise(workout, "Test Exercise", 1, 1);
        exercisesList = List.of(new Exercise(workout, "Test 1", 1, 1),
                                            new Exercise(workout, "Test 2", 2, 2),
                                            new Exercise(workout, "Test 3", 3, 3));
    }

    @Test
    void getAllExercises_returnsExercises() {
        Mockito.when(exerciseRepository.getAllExercises()).thenReturn(exercisesList);

        ResponseEntity<List<Exercise>> response = exerciseController.getAllExercises();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exercisesList, response.getBody());
    }

    @Test
    void getAllExercises_404_ifNoExercisesFound() {
        Mockito.when(exerciseRepository.getAllExercises()).thenReturn(List.of());

        ResponseEntity<List<Exercise>> response = exerciseController.getAllExercises();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getExerciseById_returnsExercise() {
        Mockito.when(exerciseRepository.getExerciseById(exercise.getId())).thenReturn(exercise);

        ResponseEntity<Exercise> response = exerciseController.getExerciseById(exercise.getId());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exercise, response.getBody());
    }

    @Test
    void getExerciseById_404_ifNoExerciseFound() {
        Mockito.when(exerciseRepository.getExerciseById(1L)).thenReturn(null);

        ResponseEntity<Exercise> response = exerciseController.getExerciseById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getExercisesByWorkoutId_returnsExercise() {
        Mockito.when(exerciseRepository.getExerciseByWorkoutId(workout.getId())).thenReturn(exercisesList);

        ResponseEntity<List<Exercise>> response = exerciseController.getExercisesByWorkoutId(workout.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exercisesList, response.getBody());
    }

    @Test
    void getExercisesByWorkoutId_404_ifNoExerciseFound() {
        Mockito.when(exerciseRepository.getExerciseByWorkoutId(1L)).thenReturn(List.of());

        ResponseEntity<List<Exercise>> response = exerciseController.getExercisesByWorkoutId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addExercise_returnsExercise() {
        ResponseEntity<Exercise> response = exerciseController.addExercise(exercise);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(exercise, response.getBody());
    }

    @Test
    void updateExercise_successfullyUpdatesExercise() {
        Mockito.when(exerciseRepository.existsById(exercise.getId())).thenReturn(true);

        ResponseEntity<Exercise> response = exerciseController.updateExercise(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exercise, response.getBody());
    }

    @Test
    void updateExercise_404_ifNoExerciseFound() {
        Mockito.when(exerciseRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Exercise> response = exerciseController.updateExercise(exercise);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
