package com.demo.kiseong.yoo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRestApiApplication.class, args);
    }

}
