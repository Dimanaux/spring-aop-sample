package app.db;

import app.db.entities.User;
import app.db.util.DatabaseConnectionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * PostgresDatabase reads users from PostgreSQL Database
 * requires org.postgresql.Driver
 * requires "user" table defined as
 * CREATE TABLE "user" (
 *     id SERIAL PRIMARY KEY,
 *     name VARCHAR(32),
 *     email VARCHAR(32),
 *     birth_date TIMESTAMP
 * );
 * tested on PostgreSQL 11.0-1 (should work with 10+)
 */
@Component
public class PostgresDatabase implements Database {
    private final Connection connection;

    @Autowired
    public PostgresDatabase(DatabaseConnectionConfiguration configuration) {
        try {
            Class.forName("org.postgresql.Driver");

            this.connection = DriverManager.getConnection(
                    configuration.getUrl(),
                    configuration.getUsername(),
                    configuration.getPassword()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("PostgreSQL Driver not found!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot connect to database!");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            LinkedList<User> users = new LinkedList<>();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\";");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDate("birth_date").toLocalDate()
                ));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE id = ? LIMIT 1");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDate("birth_date").toLocalDate()
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
