package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query(value = "SELECT * FROM \"Workout\"", nativeQuery = true)
    List<Workout> getAllWorkouts();

    @Query(value = "SELECT * FROM \"Workout\" WHERE id = :id", nativeQuery = true)
    Workout getById(long id);

    @Query(value = "SELECT * FROM \"Workout\" WHERE user_id = :userId", nativeQuery = true)
    List<Workout> getByUserId(long userId);
}