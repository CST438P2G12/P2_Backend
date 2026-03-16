package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    /**
     * GET endpoint for getting all users in the DB.
     * @return List with all the users currently in the database.
     */
    @Operation(
            summary = "Admin: Get all users",
            description = "Returns a list of all users in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    })
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.getAllUsers());
    }

    /**
     * GET endpoint for getting a single user by their ID. 404s if user does not exist.
     * @param id ID of the user to fetch
     * @return User object with the given ID, or nothing/404 if the user is not found.
     */
    @Operation(
            summary = "Admin: Get user by ID",
            description = "Returns a specific user's information based on ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * PUT endpoint for admins to update a user. 404s if user does not exist in DB.
     * @param user User object to update.
     * @return Updated user object, or nothing/404 if the user does not exist.
     */
    @Operation(
            summary = "Admin: Update user",
            description = "Updates an existing user's information."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.notFound().build();
        }
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE endpoint for admins to delete a user. 404s if user does not exist in DB.
     * @param email Email of the user to delete.
     * @return HTTP 204 if success, 404 if failure.
     */
    @Operation(
            summary = "Admin: Delete user",
            description = "Deletes a user based on ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(userRepository.getUserByEmail(email));
        return ResponseEntity.noContent().build();
    }
}