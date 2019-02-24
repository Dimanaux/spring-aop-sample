package app.db;

import app.db.entities.User;

import java.util.List;

/**
 * database interface example
 */
public interface Database {
    List<User> getAllUsers();
    User getUserById(int id);
}
