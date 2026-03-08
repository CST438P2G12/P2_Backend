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

    /**
     * GET endpoint to get all Workouts present in the DB.
     * @return Java List with all workouts in the DB.
     */
    @GetMapping("/getAllWorkouts")
    public List<Workout> getAllWorkouts() {
        return workoutRepository.getAllWorkouts();
    }

    /**
     * GET endpoint to get a single Workout by its ID.
     * @param id ID of the workout to get from the DB.
     * @return Workout object with the given ID, or an HTTP 404 if no such Workout exists.
     */
    @GetMapping("/getWorkoutById")
    public ResponseEntity<Workout> getWorkoutById(Long id) {
        Workout workout = workoutRepository.getByWorkoutId(id);
        if (workout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workout);
    }

    @GetMapping("/getWorkoutsByUser")
    public List<Workout> getWorkoutsByUser(Long userId) {
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
    public ResponseEntity<Void> deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workoutRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}