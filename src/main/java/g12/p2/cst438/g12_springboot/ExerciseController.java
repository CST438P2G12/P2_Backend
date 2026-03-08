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

    @GetMapping("/getAllExercises")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.getAllExercises();
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/getExerciseById")
    public ResponseEntity<Exercise> getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.getExerciseById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/getExercisesByWorkoutId")
    public ResponseEntity<List<Exercise>> getExercisesByWorkoutId(Long workoutId) {
        List<Exercise> exercises = exerciseRepository.getExerciseByWorkoutId(workoutId);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @PostMapping("/addExercise")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        exerciseRepository.save(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
    }

    @PutMapping("/updateExercise")
    public ResponseEntity<Exercise> updateExercise(@RequestBody Exercise exercise) {
        if (!exerciseRepository.existsById(exercise.getId())) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.save(exercise);
        return ResponseEntity.ok(exercise);
    }

    @PatchMapping("/patchExercise/reps")
    public ResponseEntity<Void> patchExerciseReps(@RequestBody Long id, @RequestBody int reps) {
        if (!exerciseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.updateRepsById(id, reps);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patchExercise/sets")
    public ResponseEntity<Void> patchExerciseSets(@RequestBody Long id, @RequestBody int sets) {
        if (!exerciseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.updateSetsById(id, sets);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/deleteExercise")
    public ResponseEntity<Void> deleteExercise(@RequestBody Exercise exercise) {
        if (!exerciseRepository.existsById(exercise.getId())) {
            return ResponseEntity.notFound().build();
        }
        exerciseRepository.delete(exercise);
        return ResponseEntity.noContent().build();
    }
}
