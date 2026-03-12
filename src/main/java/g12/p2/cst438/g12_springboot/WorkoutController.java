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

    /**
     * GET endpoint to get all Workouts present in the DB.
     * @return Java List with all workouts in the DB.
     */
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

    /**
     * GET endpoint to get a single Workout by its ID.
     * @param id ID of the workout to get from the DB.
     * @return Workout object with the given ID, or an HTTP 404 if no such Workout exists.
     */
    @Operation(
            summary = "Get workout by ID",
            description = "Returns a single workout based on its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workout found"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    @GetMapping("/getWorkoutById")
    public ResponseEntity<Workout> getWorkoutById(Long id) {
        Workout workout = workoutRepository.getByWorkoutId(id);
        if (workout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workout);
    }

    /**
     * GET endpoint to get all Workouts associated with a given User ID.
     * @param userId ID of the User to get workouts for.
     * @return Java List of Workouts associated with the provided User ID, 404s if no such Workouts exist (or User DNE)
     */
    @Operation(
            summary = "Get workouts by user",
            description = "Returns all workouts associated with a specific user ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Workouts retrieved successfully")
    })
    @GetMapping("/getWorkoutsByUser")
    public ResponseEntity<List<Workout>> getWorkoutsByUser(Long userId) {
        List<Workout> workouts =  workoutRepository.getByUserId(userId);
        if (workouts == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workouts);
    }

    /**
     * POST endpoint to add a Workout.
     * @param workout New Workout object to add to the DB.
     * @return Java Workout object that was added.
     */
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

    /**
     * PUT endpoint to update the provided Workout.
     * @param workout Updated Workout object to replace the old.
     * @return The newly-saved Workout object, or a 404 if no such Workout exists already.
     */
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

    /**
     * DELETE endpoint to delete a Workout with the provided ID from the DB.
     * @param id ID of the workout to be deleted
     * @return HTTP 204 on success, or 404 if no Workout with that ID exists in the DB.
     */
    @Operation(
            summary = "Delete workout",
            description = "Deletes a workout by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Workout deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout not found")
    })
    @DeleteMapping("/deleteWorkout")
    public ResponseEntity<Void> deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workoutRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}