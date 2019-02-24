package app.views;

import app.db.entities.User;
import com.google.gson.Gson;

import java.util.List;

/**
 * JsonView presents User as Json
 * also presents list of users as
 * { "users": [userJson1, userJson2,... userJsonN] }
 */
public class JsonView implements View<User> {
    private final Gson json;

    public JsonView(Gson jsonFactory) {
        this.json = jsonFactory;
    }

    @Override
    public String show(User user) {
        return json.toJson(user);
    }

    @Override
    public String showAll(List<User> users) {
        return String.format("{\"users\":%s}", json.toJson(users));
    }
}
