package g12.p2.cst438.g12_springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    protected User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
