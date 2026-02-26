package g12.p2.cst438.g12_springboot.entities;


import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
    private String exerciseName;
    private int reps;
    private int sets;

    protected Exercise() {}

    public Exercise(Workout workout, String exerciseName, int sets, int reps) {
        this.workout = workout;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
    }

    public long getId() { return id; }
    public Workout getWorkout() { return workout; }
    public String getExerciseName() { return exerciseName; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", exerciseName=" + exerciseName +
                ", sets=" + sets + ", reps=" + reps + "]";
    }
}




