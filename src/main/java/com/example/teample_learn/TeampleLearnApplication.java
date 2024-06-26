package com.example.teample_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@EnableJpaAuditing
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TeampleLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeampleLearnApplication.class, args);
    }

}
