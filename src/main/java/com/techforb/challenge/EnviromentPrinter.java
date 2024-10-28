package com.techforb.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnviromentPrinter implements CommandLineRunner {

    @Override
    public void run(String... args) {
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        // Registrando las variables de entorno
        System.out.println("DB_URL: " + dbUrl);
        System.out.println("DB_USERNAME: " + dbUsername);
        System.out.println("DB_PASSWORD: " + dbPassword);
        System.out.println("FRONTEND_URL: " + System.getenv("FRONTEND_URL"));
    }
}