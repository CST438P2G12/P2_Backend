package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @GetMapping("/getAllWorkouts")
    public List<Workout> getAllWorkouts() {
        return workoutRepository.getAllWorkouts();
    }

    @GetMapping("/getWorkoutById")
    public Workout getWorkoutById(long id) {
        return workoutRepository.getById(id);
    }

    @GetMapping("/getWorkoutsByUser")
    public List<Workout> getWorkoutsByUser(long userId) {
        return workoutRepository.getByUserId(userId);
    }

    @PostMapping("/addWorkout")
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout) {
        Workout saved = workoutRepository.save(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/updateWorkout")
    public ResponseEntity<Workout> updateWorkout(@RequestBody Workout workout) {
        if (!workoutRepository.existsById(workout.getId())) {
            return ResponseEntity.notFound().build();
        }
        Workout updated = workoutRepository.save(workout);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteWorkout")
    public ResponseEntity<Void> deleteWorkout(long id) {
        if (!workoutRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workoutRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}