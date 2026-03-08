package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "SELECT * FROM \"Exercise\"", nativeQuery = true)
    List<Exercise> getAllExercises();

    @Query(value = "SELECT * FROM \"Exercise\" WHERE \"exerciseId\" = :id", nativeQuery = true)
    Exercise getExerciseById(Long id);

    @Query(value = "SELECT * FROM \"Exercise\" WHERE \"workoutId\" = :id", nativeQuery = true)
    List<Exercise> getExerciseByWorkoutId(Long id);

}
