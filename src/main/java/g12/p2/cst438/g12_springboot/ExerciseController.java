package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
