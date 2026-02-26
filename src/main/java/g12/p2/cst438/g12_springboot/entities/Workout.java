package g12.p2.cst438.g12_springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate date;

    //@OneToMany(mappedBy = "workout",cascade = CascadeType.ALL)

    @Override
    public String toString() {
        return "Workout [id=" + id + ", user=" + user + ", date=" + date + "]";
    }


    protected Workout() {}

    public Workout(User user, LocalDate date) {
        this.user = user;
        this.date = date;
    }

    public long getId() { return id; }
    public User getUser() { return user; }
    public LocalDate getDate() { return date; }
}
