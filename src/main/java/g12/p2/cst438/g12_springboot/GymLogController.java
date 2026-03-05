package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GymLogController {

    private final UserRepository userRepository;

    GymLogController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns a User object with the given ID, if it exists.
     * @param id the ID of the requested User
     * @return a User object with the given ID
     */
    @GetMapping("/getUserById")
    public User user(long id) {
        return userRepository.getById(id);
    }

    /**
     * Returns all users currently in the database.
     * @return all current users in the database.
     */
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
