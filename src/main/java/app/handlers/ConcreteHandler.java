package app.handlers;

import app.db.Database;
import app.db.entities.User;
import app.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConcreteHandler implements Handler {
    private final Database database;
    private final View<User> view;

    @Autowired
    public ConcreteHandler(
            @Qualifier("postgresDatabase") Database database,
            @Qualifier("jsonView") View<User> view) {
        this.database = database;
        this.view = view;
    }

    @Override
    public String respond(String query) {
        String[] split = query.split("/");
        if (split.length == 2) { // /users
            return view.showAll(
                    database.getAllUsers()
            );
        } else { // split.length == 3 // /users/678
            int id = Integer.parseInt(split[2]);
            return view.show(
                    database.getUserById(id)
            );
        }
    }
}
