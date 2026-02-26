package g12.p2.cst438.g12_springboot.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int sets;

    @Column(name = "reps_per_set", nullable = false)
    private int repsPerSet;

    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    @Column
    private String notes;

    public Workout() {}

    public Workout(Long userId, LocalDate date, int sets, int repsPerSet, int durationMinutes, String notes) {
        this.userId = userId;
        this.date = date;
        this.sets = sets;
        this.repsPerSet = repsPerSet;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getSets() { return sets; }
    public void setSets(int sets) { this.sets = sets; }

    public int getRepsPerSet() { return repsPerSet; }
    public void setRepsPerSet(int repsPerSet) { this.repsPerSet = repsPerSet; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}