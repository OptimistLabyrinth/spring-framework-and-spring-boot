package com.demo.kiseong.yoo.demo.domain.index;

import com.demo.kiseong.yoo.demo.annotations.DefaultApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping
    @DefaultApiResponses
    public String greeting() {
        return "Welcome to Spring Framework with Spring Boot! (Java 17)";
    }

}
