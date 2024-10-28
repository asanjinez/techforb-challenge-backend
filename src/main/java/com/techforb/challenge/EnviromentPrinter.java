package com.techforb.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnviromentPrinter implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Imprimiendo variables de entorno");
        System.out.println("FRONTEND_URL: " + System.getenv("FRONTEND_URL"));
        System.out.println("MYSQL_HOST: " + System.getenv("MYSQL_HOST"));
        System.out.println("MYSQL_PORT: " + System.getenv("MYSQL_PORT"));
        System.out.println("MYSQL_USER: " + System.getenv("MYSQL_USER"));
        System.out.println("MYSQL_PASSWORD: " + System.getenv("MYSQL_PASSWORD"));
    }
}