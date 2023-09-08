package com.demo.kiseong.yoo.demo.domain.calculator.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerTests {

    @LocalServerPort
    private int port;

    final int a = 100;
    final int b = 25;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Nested
    public class GetPlus {
        @Test
        public void getPlusShouldReturnAPlusB() {
            final var responseString = given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/plus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a + b))).isTrue();
        }

        @Test
        public void getPlusShouldThrowWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/plus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getPlusShouldThrowWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/plus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostPlus {
        @Test
        public void postPlusShouldReturnAPlusB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            final var responseString = given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/plus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a + b))).isTrue();
        }

        @Test
        public void postPlusShouldThrowWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/plus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postPlusShouldThrowWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/plus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class GetMinus {
        @Test
        public void getMinusShouldReturnAMinusB() {
            final var responseString = given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/minus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a - b))).isTrue();
        }

        @Test
        public void getMinusShouldThrowWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/minus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getMinusShouldThrowWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/minus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostMinus {
        @Test
        public void postMinusShouldReturnAMinusB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            final var responseString = given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/minus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a - b))).isTrue();
        }

        @Test
        public void postMinusShouldThrowWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/minus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postMinusShouldThrowWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/minus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class GetMultiply {
        @Test
        public void getMultiplyShouldReturnAMultiplyB() {
            final var responseString = given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/multiply")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a * b))).isTrue();
        }

        @Test
        public void getMultiplyShouldThrowWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/multiply")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getMultiplyShouldThrowWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/multiply")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostMultiply {
        @Test
        public void postMultiplyShouldReturnAMultiplyB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            final var responseString = given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/multiply")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a * b))).isTrue();
        }

        @Test
        public void postMultiplyShouldThrowWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/multiply")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postMultiplyShouldThrowWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/multiply")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class GetDivide {
        @Test
        public void getDivideShouldReturnADivideB() {
            final var responseString = given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/divide")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a / b))).isTrue();
        }

        @Test
        public void getDivideShouldThrowWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getDivideShouldThrowWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getDivideShouldThrowWhenBIsZero() {
            given().queryParam("a", a).queryParam("b", 0)
                .when().get("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostDivide {
        @Test
        public void postDivideShouldReturnADivideB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            final var responseString = given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/divide")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a / b))).isTrue();
        }

        @Test
        public void postDivideShouldThrowWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postDivideShouldThrowWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postDivideShouldThrowWhenBIsZero() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", 0);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/divide")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class GetModulus {
        @Test
        public void getModulusShouldReturnAModulusB() {
            final var responseString = given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/modulus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a % b))).isTrue();
        }

        @Test
        public void getDivideShouldThrowWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/modulus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getDivideShouldThrowWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/modulus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostModulus {
        @Test
        public void postModulusShouldReturnAModulusB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            final var responseString = given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/modulus")
                .then().extract().body().asString();
            assertThat(responseString.equals(String.format("%d", a % b))).isTrue();
        }

        @Test
        public void postModulusShouldThrowWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/modulus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postModulusShouldThrowWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/modulus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postModulusShouldThrowWhenBIsZero() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", 0);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/modulus")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class GetBasicAll {
        @Test
        public void getBasicAllShouldReturnBasicArithmeticResultsOfAAndB() {
            given().queryParam("a", a).queryParam("b", b)
                .when().get("/calculator/basic-all")
                .then()
                .body("plus", equalTo(a + b))
                .body("minus", equalTo(a - b))
                .body("multiply", equalTo(a * b))
                .body("divide", equalTo(a / b))
                .body("modulus", equalTo(a % b));
        }

        @Test
        public void getBasicAllThrowsWhenAIsMissing() {
            given().queryParam("b", b)
                .when().get("/calculator/basic-all")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void getBasicAllThrowsWhenBIsMissing() {
            given().queryParam("a", a)
                .when().get("/calculator/basic-all")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    public class PostBasicAll {
        @Test
        public void postBasicAllShouldReturnBasicArithmeticResultsOfAAndB() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/basic-all")
                .then()
                .body("plus", equalTo(a + b))
                .body("minus", equalTo(a - b))
                .body("multiply", equalTo(a * b))
                .body("divide", equalTo(a / b))
                .body("modulus", equalTo(a % b));
        }

        @Test
        public void postBasicAllThrowsWhenAIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("b", b);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/basic-all")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postBasicAllThrowsWhenBIsMissing() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/basic-all")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        public void postBasicAllThrowsWhenBIsZero() {
            final Map<String, Integer> entity = new HashMap<>();
            entity.put("a", a);
            entity.put("b", 0);
            given().contentType(ContentType.JSON).body(entity)
                .when().post("/calculator/basic-all")
                .then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

}
