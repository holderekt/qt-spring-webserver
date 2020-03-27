/**
 * Main Application
 * @author Ivan Diliso
 */

package webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application - Esecuzione del webserver
 */

@SpringBootApplication
public class Application {

    /**
     * Esecuzione della main application di Spring Boot
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}