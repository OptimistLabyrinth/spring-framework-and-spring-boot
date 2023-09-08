package com.demo.kiseong.yoo.demo.domain.user.api;

import com.demo.kiseong.yoo.demo.domain.user.TestUserCommon;
import com.demo.kiseong.yoo.demo.domain.user.api.dto.UserResponseDto;
import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import com.demo.kiseong.yoo.demo.domain.user.infra.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateUserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private final String editedFirstName = "updated-test-first-name";
    private final String editedLastName = "updated-test-first-name";
    private final String editedEmail = "updated-test-user@email.com";

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
    @DisplayName("succeed to update user with valid id and valid input")
    public void updateUserSuccess() throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", editedLastName)
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + user.getId())
                // * then
                .then()
                .extract().asString();
        System.out.println(responseString);
        UserResponseDto.Basic userResponse = objectMapper.readValue(responseString, UserResponseDto.Basic.class);
        assertThat(userResponse.getId()).isEqualTo(user.getId());
        assertThat(userResponse.getFirstName()).isEqualTo(editedFirstName);
        assertThat(userResponse.getLastName()).isEqualTo(editedLastName);
        assertThat(userResponse.getEmail()).isEqualTo(editedEmail);
        assertThat(userResponse.getCreatedAt().toEpochSecond(ZoneOffset.UTC)).isEqualTo(user.getCreatedAt().toEpochSecond(ZoneOffset.UTC));
        assertThat(userResponse.getUpdatedAt().toEpochSecond(ZoneOffset.UTC)).isEqualTo(user.getUpdatedAt().toEpochSecond(ZoneOffset.UTC));
    }

    @Test
    @DisplayName("fail to update user with invalid id")
    public void updateUserFailureWhenInvalidId() throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", editedLastName)
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId() + 1))
                // * then
                .then()
                .extract().asString();
        assertThat(responseString.contains("user not found")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to update user when first name is null or empty string")
    public void updateUserFailureWhenFirstNameIsNullOrEmptyString(String firstNameParam) throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", firstNameParam)
            .put("lastName", editedLastName)
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @Test
    @DisplayName("fail to update user when first name is blank space")
    public void updateUserFailureWhenFirstNameIsBlank() throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", " ")
            .put("lastName", editedLastName)
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to update user when last name is null or empty string")
    public void updateUserFailureWhenLastNameIsNullOrEmptyString(String lastNameParam) throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", lastNameParam)
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @Test
    @DisplayName("fail to update user when last name is blank space")
    public void updateUserFailureWhenLastNameIsBlank() throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", " ")
            .put("email", editedEmail);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to update user when email is null or empty string")
    public void updateUserFailureWhenEmailIsNullOrEmptyString(String emailParam) throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", editedLastName)
            .put("email", emailParam);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        System.out.println(responseString);
        assertThat(responseString.contains("must not be empty")).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "test-address", "test address", "test-address@", "@email-domain", "@email-domain.com",
        " "
    })
    @DisplayName("fail to update user when email is not in email format")
    public void updateUserFailureWhenEmailIsNotInEmailFormat(String emailParam) throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", editedFirstName)
            .put("lastName", editedLastName)
            .put("email", emailParam);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .patch("/user/" + (user.getId()))
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must be a well-formed email address")).isTrue();
    }

}
