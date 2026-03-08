package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import g12.p2.cst438.g12_springboot.entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        user = new User("Test User");
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

        Workout result = workoutController.getWorkoutById(1L);

        assertNotNull(result);
        assertEquals(workout, result);
    }

    @Test
    void getWorkoutById_returnsNull() {
        when(workoutRepository.getByWorkoutId(1L)).thenReturn(null);

        Workout testWorkout = workoutController.getWorkoutById(1L);

        assertNull(testWorkout);
    }

    @Test
    void getWorkoutsByUser_returnsWorkouts() {
        List<Workout> workouts = List.of(new Workout(user, LocalDate.of(1900, 1, 1)),
                                            new Workout(user, LocalDate.of(1900, 1, 2)));
                when(workoutRepository.getByUserId(user.getId())).thenReturn(workouts);

        List<Workout> result = workoutController.getWorkoutsByUser(user.getId());

        assertFalse(result.isEmpty());
        assertEquals(workouts, result);
    }

    @Test
    void getWorkoutsByUser_returnsNull() {
        when(workoutRepository.getByUserId(1L)).thenReturn(null);

        List<Workout> result = workoutController.getWorkoutsByUser(1L);

        assertNull(result);
    }
}
