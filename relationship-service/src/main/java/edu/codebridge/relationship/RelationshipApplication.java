package edu.codebridge.relationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "edu.codebridge.feign.client")
public class RelationshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationshipApplication.class, args);
    }

}