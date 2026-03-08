package g12.p2.cst438.g12_springboot.entities;


import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long exerciseId;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;
    private String exerciseName;
    private int reps;
    private int sets;

    protected Exercise() {}

    /**
     * Constructor for an Exercise object. Takes a Workout to be correlated with,
     * the name of the workout as a String, and ints for sets and reps.
     * @param workout Workout that the Exercise took place as part of.
     * @param exerciseName Name/type of Exercise
     * @param sets Number of sets for the Exercise
     * @param reps Number of reps per set
     */
    public Exercise(Workout workout, String exerciseName, int sets, int reps) {
        this.workout = workout;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
    }

    /**
     * Getter for the Exercise's ID.
     * @return Exercise's ID
     */
    public long getId() { return exerciseId; }

    /**
     * Getter for the Workout correlated with the Exercise.
     * @return Workout object that the Exercise is associated with.
     */
    public Workout getWorkout() { return workout; }

    /**
     * Getter for the Exercise's name/type/etc
     * @return String with the Exercise's name
     */
    public String getExerciseName() { return exerciseName; }

    /**
     * Getter for the number of sets of the Exercise.
     * @return int with the amount of sets for the Exercise.
     */
    public int getSets() { return sets; }

    /**
     * Getter for the number of reps per set of the Exercise.
     * @return int with the amount of reps per set for the Exercise.
     */
    public int getReps() { return reps; }

    /**
     * Overriden toString for Exercise.
     * @return String that displays the values of all the Exercise's fields.
     */
    @Override
    public String toString() {
        return "Exercise [id=" + exerciseId + ", exerciseName=" + exerciseName +
                ", sets=" + sets + ", reps=" + reps + "]";
    }
}




