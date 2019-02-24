package app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * sample of logger
 */
@Aspect
public class Logger {
    /**
     * runs before execution of any method call
     * in package app.db
     * @param joinPoint - information on method call
     */
    @Before("execution(* app.db..*.*(..))")
    public void log(JoinPoint joinPoint) {
        System.out.println("LOG: method call " + joinPoint.getSignature().toString());
    }
}
