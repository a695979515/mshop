package net.mshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MshopApplication.class, args);
    }
}
