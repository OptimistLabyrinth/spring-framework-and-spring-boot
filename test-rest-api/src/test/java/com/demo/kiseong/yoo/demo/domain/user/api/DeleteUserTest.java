package com.demo.kiseong.yoo.demo.domain.user.api;

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
public class DeleteUserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user = null;


    @BeforeAll
    public void setupAll() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        userRepository.deleteAll();
    }

    @BeforeEach
    public void setupEach() {
        if (user != null) {
            userRepository.findById(user.getId()).ifPresent(userRepository::delete);
        }
        user = userRepository.save(new User("test-first-name", "test-last-name", "test-user@email.com"));
    }

    @Test
    @DisplayName("succeed to delete user with valid id")
    public void deleteUserSuccess() throws JsonProcessingException {
        var responseString =
            // * given
            given()
                // * when
                .when()
                .delete("/user/" + user.getId())
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
    @DisplayName("fail to delete user with invalid id")
    public void deleteUserFailureWithInvalidUserId() {
        var responseString =
            // * given
            given()
                // * when
                .when()
                .delete("/user/" + user.getId() + 1)
                // * then
                .then()
                .extract().asString();
        assertThat(responseString.contains("user not found")).isTrue();
    }

}
