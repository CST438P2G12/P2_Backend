package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GymLogController {

    private final UserRepository userRepository;

    GymLogController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getUserById")
    public User user(long id) {
        return userRepository.getById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
