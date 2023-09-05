package com.demo.kiseong.yoo.demo.domain.user.api;

import com.demo.kiseong.yoo.demo.domain.user.TestUserCommon;
import com.demo.kiseong.yoo.demo.domain.user.api.dto.UserResponseDto;
import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import com.demo.kiseong.yoo.demo.domain.user.infra.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeAll
    public void setup() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        userRepository.deleteAll();
    }

    @BeforeEach
    public void setupEach() {
        if (user != null) {
            userRepository.delete(user);
            user = null;
        }
        user = userRepository.save(new User(TestUserCommon.firstName, TestUserCommon.lastName, TestUserCommon.email));
    }

    @Test
    @DisplayName("succeed to find user with valid id")
    public void findUserSuccess() throws JsonProcessingException {
        var responseString =
            // * given
            given()
                // * when
                .when()
                .get("/user/" + user.getId())
                // * then
                .then()
                .extract().asString();
        UserResponseDto.Basic userResponse = objectMapper.readValue(responseString, UserResponseDto.Basic.class);
        assertThat(userResponse.getId()).isEqualTo(user.getId());
        assertThat(userResponse.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userResponse.getLastName()).isEqualTo(user.getLastName());
        assertThat(userResponse.getEmail()).isEqualTo(user.getEmail());
        assertThat(userResponse.getCreatedAt().toEpochSecond(ZoneOffset.UTC)).isEqualTo(user.getCreatedAt().toEpochSecond(ZoneOffset.UTC));
        assertThat(userResponse.getUpdatedAt().toEpochSecond(ZoneOffset.UTC)).isEqualTo(user.getUpdatedAt().toEpochSecond(ZoneOffset.UTC));
    }

    @Test
    @DisplayName("fail to find user with invalid id")
    public void findUserFailureWhenIdNotFound() {
        var responseString =
            // * given
            given()
                // * when
                .when()
                .get("/user/" + (user.getId() + 1))
                // * then
                .then()
                .extract().asString();
        assertThat(responseString.contains("user not found")).isTrue();
    }

}
