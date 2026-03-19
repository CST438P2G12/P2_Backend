package g12.p2.cst438.g12_springboot;

import g12.p2.cst438.g12_springboot.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * GET endpoint to retrieve a user by their email.
     * @param email Email of the user to retrieve.
     * @return User object with the given email, or 404 if no such user exists.
     */
    @Operation(
            summary = "Get user by email",
            description = "Returns a single user based on their email address."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * GET endpoint to retrieve a user by their name.
     * @param name Name of the user to retrieve.
     * @return User object with the given name, or 404 if no such user exists.
     */
    @Operation(
            summary = "Get user by name",
            description = "Returns a single user based on their name."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/getUserByName")
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        User user = userRepository.getByName(name);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * GET endpoint to check if a user is an admin by their email.
     * @param email Email of the user to check.
     * @return Boolean indicating admin status, or 404 if no such user exists.
     */
    @Operation(
            summary = "Check if user is admin",
            description = "Returns whether the user with the given email has admin privileges."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin status retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRepository.isAdmin(email));
    }

    /**
     * POST endpoint to register a new user.
     * @param user User object to add to the DB.
     * @return HTTP 201 on success, or 409 if a user with that email already exists.
     */
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user entry in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "409", description = "User with that email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userRepository.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * PUT endpoint for a user to update their own information.
     * @param user Updated User object.
     * @return Updated User object, or 404 if no such user exists.
     */
    @Operation(
            summary = "Update user",
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
     * DELETE endpoint for a user to delete their own account.
     * @param email Email of the user to delete.
     * @return HTTP 204 on success, 404 if no such user exists.
     */
    @Operation(
            summary = "Delete user",
            description = "Deletes a user account by email."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(userRepository.getUserByEmail(email));
        return ResponseEntity.noContent().build();
    }
}
