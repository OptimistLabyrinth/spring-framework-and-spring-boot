package com.demo.kiseong.yoo.demo.domain.index;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTests {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private IndexController indexController;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void greetingShouldReturnWelcomingMessageRestAssured() {
        final var responseString = given()
            .when().get("/")
            .then().extract().body().asString();
        assertThat(responseString.contains("Welcome")).isTrue();
    }

    @Test
    public void greetingShouldReturnWelcomingMessageTestRestTemplate() {
        final var responseEntity = restTemplate.getForEntity(String.format("http://localhost:%d/", port), String.class);
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.OK)).isTrue();
        final var responseBody = responseEntity.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.contains("Welcome")).isTrue();
    }

}
