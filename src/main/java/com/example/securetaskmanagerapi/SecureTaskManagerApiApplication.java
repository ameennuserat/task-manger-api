package com.example.securetaskmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
public class SecureTaskManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureTaskManagerApiApplication.class, args);
    }

}
