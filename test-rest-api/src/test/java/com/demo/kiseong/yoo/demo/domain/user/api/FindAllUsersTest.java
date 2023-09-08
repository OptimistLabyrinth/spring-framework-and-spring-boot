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
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllUsersTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<User> users;

    @BeforeAll
    public void setupAll() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        userRepository.deleteAll();
        users = userRepository.saveAll(List.of(
            new User("test-first-name-01", "test-last-name-01", "test-01-user@email.com"),
            new User("test-first-name-02", "test-last-name-02", "test-02-user@email.com"),
            new User("test-first-name-03", "test-last-name-03", "test-03-user@email.com")
        ));
    }

    @Test
    @DisplayName("succeed to find all users")
    public void findAllUsersSuccess() throws JsonProcessingException {
        var responseString =
            // * given
            given()
                // * when
                .when()
                .get("/user")
                // * then
                .then()
                .extract().asString();
        List<UserResponseDto.Basic> usersResponse = List.of(objectMapper.readValue(responseString, UserResponseDto.Basic[].class));

        assertThat(usersResponse.size()).isEqualTo(users.size());
        for (var i = 0; i < usersResponse.size(); ++i) {
            assertThat(usersResponse.get(i).getId()).isEqualTo(users.get(i).getId());
            assertThat(usersResponse.get(i).getFirstName()).isEqualTo(users.get(i).getFirstName());
            assertThat(usersResponse.get(i).getLastName()).isEqualTo(users.get(i).getLastName());
            assertThat(usersResponse.get(i).getEmail()).isEqualTo(users.get(i).getEmail());
            assertThat(usersResponse.get(i).getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .isEqualTo(users.get(i).getCreatedAt().toEpochSecond(ZoneOffset.UTC));
            assertThat(usersResponse.get(i).getUpdatedAt().toEpochSecond(ZoneOffset.UTC))
                .isEqualTo(users.get(i).getUpdatedAt().toEpochSecond(ZoneOffset.UTC));
        }
    }

}
