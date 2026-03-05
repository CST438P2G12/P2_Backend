package g12.p2.cst438.g12_springboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private boolean isAdmin;

    protected User() {
    }

    /**
     * Constructor for a User object. Initializes a User with the given name and defaults user to not an admin.
     * @param name Profile name of the User
     */
    public User(String name) {
        this.name = name;
        this.isAdmin = false;
    }

    /**
     * toString of a User object. Returns a String with every field for the User.
     * @return String of User's id, name, and if they are an admin.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    /**
     * Getter for User.id
     * @return ID for the User
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for User.name
     * @return User's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for if User is an admin or not.
     * @return boolean for User's admin status
     */
    public boolean isAdmin() {
        return isAdmin;
    }
}
