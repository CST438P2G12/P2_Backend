package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import g12.p2.cst438.g12_springboot.entities.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    }

    @Test
    void getAllWorkouts_returnsWorkouts() {

    }
}
