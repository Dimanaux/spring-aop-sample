package app.db.util;

/**
 * data class, containing required info for establishing
 * database connection (JDBC)
 * can be configured in spring-config.xml
 */
public class DatabaseConnectionConfiguration {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConnectionConfiguration(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
