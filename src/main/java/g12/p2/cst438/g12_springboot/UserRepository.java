package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM \"User\"", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM \"User\" WHERE id = :id", nativeQuery = true)
    User getUserById(Long id);

    @Query(value = "SELECT * FROM \"User\" WHERE name = :name", nativeQuery = true)
    User getByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM \"User\" WHERE id = :id", nativeQuery = true)
    void deleteById(Long id);
}
