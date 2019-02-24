package app;

import app.handlers.ConcreteHandler;
import app.handlers.Handler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author barskoidim@gmail.com
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-config.xml");
        Handler handler = applicationContext.getBean(ConcreteHandler.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String help = "supported queries:\n" +
                "/users/:id -- find user by id\n" +
                "/users/ -- find all users\n" +
                "exit -- terminate the program\n";

        System.out.println(help);
        System.out.println("Input query:");
        String query = reader.readLine();

        while (!"exit".equals(query)) {
            System.out.println(handler.respond(query));

            System.out.println("\nInput query:");
            query = reader.readLine();
        }

    }
}
