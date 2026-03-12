package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(
            summary = "Admin: Get all users",
            description = "Returns a list of all users in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    })
    // View all users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Operation(
            summary = "Admin: Get user by ID",
            description = "Returns a specific user's information based on ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    // View a user's info by ID
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Admin: Update user",
            description = "Updates an existing user's information."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    // Edit a user's info
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!userRepository.existsById(user.getId())) {
            return ResponseEntity.notFound().build();
        }
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Admin: Delete user",
            description = "Deletes a user based on ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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