package g12.p2.cst438.g12_springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    protected Workout() {}

    /**
     * Public constructor for Workout. Takes a User object and a LocalDate of the workout.
     * @param user User that added the Workout.
     * @param date Date of the Workout.
     */
    public Workout(User user, LocalDate date) {
        this.user = user;
        this.date = date;
    }

    /**
     * Getter for the Workout's ID.
     * @return ID of the requested workout.
     */
    public Long getId() { return workoutId; }

    /**
     * Gets the user correlated with a particular workout.
     * @return User object that added the workout.
     */
    public User getUser() { return user; }

    /**
     * Getter for the date of the workout.
     * @return LocalDate type with the date of the workout.
     */
    public LocalDate getDate() { return date; }

    /**
     * Setter for the workout's date
     * @param date LocalDate type with the new date to set.
     */
    public void setDate(LocalDate date) { this.date = date; }
}