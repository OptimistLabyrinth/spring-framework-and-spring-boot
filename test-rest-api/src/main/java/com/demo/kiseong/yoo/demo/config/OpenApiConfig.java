package com.demo.kiseong.yoo.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public OpenAPI setupSwaggerConfig() {
        return new OpenAPI()
            .info(new Info().title("Test Rest Api Swagger Documentations")
                .description("using Spring Framework based on Spring Boot")
                .version("0.1.0"))
            .servers(List.of(
                new Server()
                    .description("local api server")
                    .url("http://localhost:" + serverPort + "/")
            ));
    }

}
