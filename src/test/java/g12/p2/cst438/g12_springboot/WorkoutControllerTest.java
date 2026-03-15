package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import g12.p2.cst438.g12_springboot.entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkoutControllerTest {

    @Mock
    private WorkoutRepository workoutRepository;

    @InjectMocks
    private WorkoutController workoutController;

    private User user;
    private Workout workout;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Test User", "test@test.com");
        workout = new Workout(user, LocalDate.of(1900, 1, 1));
    }

    @Test
    void getAllWorkouts_returnsWorkouts() {
        List<Workout> workouts = List.of(new Workout(user, LocalDate.of(1900, 1, 1)),
                new Workout(user, LocalDate.of(1900, 1, 2)));
        when(workoutRepository.getAllWorkouts()).thenReturn(workouts);

        List<Workout> result = workoutController.getAllWorkouts();

        assertFalse(result.isEmpty());
        assertEquals(result.size(), workouts.size());
        assertEquals(workouts, result);
    }

    @Test
    void getAllWorkouts_returnsNull() {
        when(workoutRepository.getAllWorkouts()).thenReturn(null);

        List<Workout> result = workoutController.getAllWorkouts();

        assertNull(result);
    }

    @Test
    void getWorkoutById_returnsWorkout() {
        when(workoutRepository.getByWorkoutId(1L)).thenReturn(workout);

        ResponseEntity<Workout> result = workoutController.getWorkoutById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(workout, result.getBody());
    }

    @Test
    void getWorkoutById_returnsNull() {
        when(workoutRepository.getByWorkoutId(1L)).thenReturn(null);

        ResponseEntity<Workout> testWorkout = workoutController.getWorkoutById(1L);

        assertEquals(HttpStatus.NOT_FOUND, testWorkout.getStatusCode());
    }

    @Test
    void getWorkoutsByUser_returnsWorkouts() {
        List<Workout> workouts = List.of(new Workout(user, LocalDate.of(1900, 1, 1)),
                                            new Workout(user, LocalDate.of(1900, 1, 2)));
                when(workoutRepository.getByUserId(user.getId())).thenReturn(workouts);

        ResponseEntity<List<Workout>> result = workoutController.getWorkoutsByUser(user.getId());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(workouts, result.getBody());
    }

    @Test
    void getWorkoutsByUser_returnsNull() {
        when(workoutRepository.getByUserId(1L)).thenReturn(null);

        ResponseEntity<List<Workout>> result = workoutController.getWorkoutsByUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void addWorkout_successfullyAddsWorkout() {
        when(workoutRepository.save(workout)).thenReturn(workout);

        ResponseEntity<Workout> result = workoutController.addWorkout(workout);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(workout, result.getBody());
    }

    @Test
    void updateWorkout_successfullyUpdatesWorkout() {
        when(workoutRepository.existsById(workout.getId())).thenReturn(true);
        when(workoutRepository.save(workout)).thenReturn(workout);

        ResponseEntity<Workout> result = workoutController.updateWorkout(workout);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(workout, result.getBody());
    }

    @Test
    void updateWorkout_404_WhenWorkoutNotFound() {
        when(workoutRepository.existsById(workout.getId())).thenReturn(false);

        ResponseEntity<Workout> result = workoutController.updateWorkout(workout);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void deleteWorkout_successfullyDeletesWorkout() {
        when(workoutRepository.existsById(workout.getId())).thenReturn(true);

        ResponseEntity<Void> result = workoutController.deleteWorkout(workout.getId());

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteWorkout_404_WhenWorkoutNotFound() {
        when(workoutRepository.existsById(workout.getId())).thenReturn(false);

        ResponseEntity<Void> result = workoutController.deleteWorkout(workout.getId());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
