package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // View all users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // View a user's info by ID
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Edit a user's info
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!userRepository.existsById(user.getId())) {
            return ResponseEntity.notFound().build();
        }
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    // Delete a user
    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}