package app.views;

import java.util.List;

/**
 * @param <T> - model to present,
 *           supposed to have getters
 *           for fields to be presented
 *           e.g. if model have only getName()
 *           it will be presented only by its name
 */
public interface View<T> {
    String show(T user);
    String showAll(List<T> users);
}
