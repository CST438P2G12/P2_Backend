package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * GET endpoint to get all Exercises currently in the DB.
     * @return Java List of all the Exercises in the DB, or a 404 if none currently present.
     */
    @GetMapping("/getAllExercises")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.getAllExercises();
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    /**
     * GET endpoint to retrieve a single Exercise by its ID.
     * @param id ID of the exercise to retrieve.
     * @return The Exercise with the given ID, or 404s if no such Exercise exists.
     */
    @GetMapping("/getExerciseById")
    public ResponseEntity<Exercise> getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.getExerciseById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    /**
     * GET endpoint to retrieve Exercises by their associated Workout.
     * @param workoutId ID of the Workout to get Exercises for.
     * @return Java List of Exercises associated with a given Workout, or a 404 if no such Exercises exist.
     */
    @GetMapping("/getExercisesByWorkoutId")
    public ResponseEntity<List<Exercise>> getExercisesByWorkoutId(Long workoutId) {
        List<Exercise> exercises = exerciseRepository.getExerciseByWorkoutId(workoutId);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    /**
     * POST endpoint to add a new Exercise.
     * @param exercise Exercise being added.
     * @return Exercise object that was created.
     */
    @PostMapping("/addExercise")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        exerciseRepository.save(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
    }

    /**
     * PUT endpoint to fully update an Exercise. 404s if no Exercise with that ID found.
     * @param exercise Exercise object to update.
     * @return Exercise object that was updated, or will 404 if no Exercise with that ID found.
     */
    @PutMapping("/updateExercise")
    public ResponseEntity<Exercise> updateExercise(@RequestBody Exercise exercise) {
        if (!exerciseRepository.existsById(exercise.getId())) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.save(exercise);
        return ResponseEntity.ok(exercise);
    }

    /**
     * PATCH endpoint to update only the number of reps for a given Exercise.
     * @param id ID of the exercise to update.
     * @param reps Number of reps to update the Exercise with.
     * @return Empty HTTP 200 on success, 404s if no Exercise with that ID found.
     */
    @PatchMapping("/patchExercise/reps")
    public ResponseEntity<Void> patchExerciseReps(@RequestBody Long id, @RequestBody int reps) {
        if (!exerciseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.updateRepsById(id, reps);
        return ResponseEntity.ok().build();
    }

    /**
     * PATCH endpoint to update only the number of sets for a given Exercise.
     * @param id ID of the Exercise to update.
     * @param sets Number of sets to update the Exercise with.
     * @return Empty HTTP 200 on request, 404s if no Exercise with that ID found.
     */
    @PatchMapping("/patchExercise/sets")
    public ResponseEntity<Void> patchExerciseSets(@RequestBody Long id, @RequestBody int sets) {
        if (!exerciseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.updateSetsById(id, sets);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE endpoint to remove an exercise from the DB.
     * @param exercise Exercise to delete from the DB.
     * @return HTTP 204 if success, HTTP 404 if no Exercise with that ID exists.
     */
    @DeleteMapping("/deleteExercise")
    public ResponseEntity<Void> deleteExercise(@RequestBody Exercise exercise) {
        if (!exerciseRepository.existsById(exercise.getId())) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.delete(exercise);
        return ResponseEntity.noContent().build();
    }
}
