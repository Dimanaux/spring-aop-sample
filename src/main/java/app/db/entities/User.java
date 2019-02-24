package app.db.entities;

import java.time.LocalDate;

/**
 * User example
 */
public class User {
    private final int id;
    private final String name;
    private final String email;
    private final LocalDate birthDate;

    public User(int id, String name, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
