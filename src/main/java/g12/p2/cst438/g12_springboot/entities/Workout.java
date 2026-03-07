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

    public Workout() {}

    public Workout(User user, LocalDate date) {
        this.user = user;
        this.date = date;
    }

    public Long getId() { return workoutId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}