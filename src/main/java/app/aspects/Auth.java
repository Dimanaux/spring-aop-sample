package app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * sample of auth aspect
 */
@Aspect
public class Auth {
    /**
     * runs on every method call in package app.db
     * checks permissions and if it is not allowed to
     * call this method aborts it's invocation
     * @param joinPoint - method call metadata
     * @return nothing but method's result
     * @throws Throwable - proceed side effect
     */
    @Around("execution(* app.db..*.*(..))")
    private Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!isAllowed()) {
            System.out.println("Forbidden call: " + joinPoint.getSignature());
            return null;
        } else {
            System.out.println("Allowed call: " + joinPoint.getSignature());
            return joinPoint.proceed(joinPoint.getArgs());
        }
    }

    private boolean isAllowed() {
        return true;
    }
}
