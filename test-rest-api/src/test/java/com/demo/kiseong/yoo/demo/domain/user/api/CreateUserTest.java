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

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateUserTest {

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
    }

    @Test
    @DisplayName("succeed to create user with valid input")
    public void createUserSuccess() throws JSONException, JsonProcessingException {
        JSONObject json = new JSONObject()
            .put("firstName", TestUserCommon.firstName)
            .put("lastName", TestUserCommon.lastName)
            .put("email", TestUserCommon.email);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .extract().body().asString();
        UserResponseDto.Basic userResponse = objectMapper.readValue(responseString, UserResponseDto.Basic.class);
        user = userRepository.findById(userResponse.getId()).orElseThrow(() -> new InternalError("failed to save user"));
        assertThat(userResponse.getId()).isNotNull();
        assertThat(userResponse.getFirstName()).isEqualTo(TestUserCommon.firstName);
        assertThat(userResponse.getLastName()).isEqualTo(TestUserCommon.lastName);
        assertThat(userResponse.getEmail()).isEqualTo(TestUserCommon.email);
        assertThat(userResponse.getCreatedAt()).isNotNull();
        assertThat(userResponse.getUpdatedAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to create user when first name is null or empty string")
    public void createUserFailureWhenFirstNameIsNullOrEmpty(String firstNameParam) throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", firstNameParam)
            .put("lastName", TestUserCommon.lastName)
            .put("email", TestUserCommon.email);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @Test
    @DisplayName("fail to create user when first name is blank spaces")
    public void createUserFailureWhenFirstNameIsBlank() throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", " ")
            .put("lastName", TestUserCommon.lastName)
            .put("email", TestUserCommon.email);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to create user when last name is null or empty string")
    public void createUserFailureWhenLastNameIsNullOrEmptyString(String lastNameParam) throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", TestUserCommon.firstName)
            .put("lastName", lastNameParam)
            .put("email", TestUserCommon.email);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @Test
    @DisplayName("fail to create user when last name is blank space")
    public void createUserFailureWhenLastNameIsBlank() throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", TestUserCommon.firstName)
            .put("lastName", " ")
            .put("email", TestUserCommon.email);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be blank")).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("fail to create user when email is null or empty string")
    public void createUserFailureWhenEmailIsNullOrEmptyString(String emailParam) throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", TestUserCommon.firstName)
            .put("lastName", TestUserCommon.lastName)
            .put("email", emailParam);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must not be empty")).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "test-address", "test address", "test-address@", "@email-domain", "@email-domain.com",
        " "
    })
    @DisplayName("fail to create user when email is not in email format")
    public void createUserFailureWhenEmailIsNotInFormat(String emailParam) throws JSONException {
        JSONObject json = new JSONObject()
            .put("firstName", TestUserCommon.firstName)
            .put("lastName", TestUserCommon.lastName)
            .put("email", emailParam);
        var responseString =
            // * given
            given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                // * when
                .when()
                .post("/user")
                // * then
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().asString();
        assertThat(responseString.contains("must be a well-formed email address")).isTrue();
    }

}
