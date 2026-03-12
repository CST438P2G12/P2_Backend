package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Workout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Operation(
            summary = "Get all workouts",
            description = "Returns a list of all workouts in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workouts retrieved successfully")
    })

    @GetMapping("/getAllWorkouts")
    public List<Workout> getAllWorkouts() {
        return workoutRepository.getAllWorkouts();
    }

    @Operation(
            summary = "Get workout by ID",
            description = "Returns a single workout based on its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workout found"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })

    @GetMapping("/getWorkoutById")
    public Workout getWorkoutById(long id) {
        return workoutRepository.getById(id);
    }

    @Operation(
            summary = "Get workouts by user",
            description = "Returns all workouts associated with a specific user ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workouts retrieved successfully")
    })

    @GetMapping("/getWorkoutsByUser")
    public List<Workout> getWorkoutsByUser(long userId) {
        return workoutRepository.getByUserId(userId);
    }

    @Operation(
            summary = "Add a workout",
            description = "Creates a new workout entry."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Workout created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid workout data")
    })

    @PostMapping("/addWorkout")
    public ResponseEntity<Workout> addWorkout(@RequestBody Workout workout) {
        Workout saved = workoutRepository.save(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(
            summary = "Update workout",
            description = "Updates an existing workout."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workout updated successfully"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })

    @PutMapping("/updateWorkout")
    public ResponseEntity<Workout> updateWorkout(@RequestBody Workout workout) {
        if (!workoutRepository.existsById(workout.getId())) {
            return ResponseEntity.notFound().build();
        }
        Workout updated = workoutRepository.save(workout);
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Delete workout",
            description = "Deletes a workout by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Workout deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })

    @DeleteMapping("/deleteWorkout")
    public ResponseEntity<Void> deleteWorkout(long id) {
        if (!workoutRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workoutRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}