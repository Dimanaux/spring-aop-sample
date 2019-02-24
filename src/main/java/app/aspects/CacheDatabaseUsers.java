package app.aspects;

import app.db.entities.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Caches Database's methods calls
 * I'm against this class
 * I prefer to use CachedDatabase wrapper instead
 * database = new CachedDatabase(database);
 */
@Aspect
public class CacheDatabaseUsers {
    private final Map<Integer, User> cache = new TreeMap<>();
    private boolean cachedAll = false;

    /**
     * checks if all all users have been read
     * returns them from cache if they have
     * otherwise reads it from database and caches
     * @param joinPoint - info about method call and functionality
     *                  such as proceed method execution
     * @return <pre>List<User></pre>
     * @throws Throwable - may be thrown by proceed method call
     */
    @Around("execution(* app.db.*.getAllUsers(..))")
    public Object cacheDatabaseCallAll(ProceedingJoinPoint joinPoint) throws Throwable {
        if (cachedAll) {
            System.out.println("taking from cache...");
            return List.of(cache.values());
        } else {
            List<User> users = (List<User>) joinPoint.proceed(joinPoint.getArgs());
            users.forEach(u -> cache.put(u.getId(), u));
            cachedAll = true;
            return users;
        }
    }

    /**
     * checks by user.id if one is in cache
     * returns him if he is
     * otherwise gets him from database and saves to cache
     * @param joinPoint - info about method call and functionality
     *                  such as proceed method execution
     * @return User - if found else null
     * @throws Throwable - may be thrown by proceed method call
     */
    @Around("execution(* app.db.*.getUserById(..))")
    public Object cacheDatabaseCallById(ProceedingJoinPoint joinPoint) throws Throwable {
        int id = (int) joinPoint.getArgs()[0];
        if (cachedAll || cache.containsKey(id)) {
            System.out.println("taking from cache...");
            return cache.get(id);
        } else {
            User user = (User) joinPoint.proceed(joinPoint.getArgs());
            cache.put(user.getId(), user);
            return user;
        }
    }
}
