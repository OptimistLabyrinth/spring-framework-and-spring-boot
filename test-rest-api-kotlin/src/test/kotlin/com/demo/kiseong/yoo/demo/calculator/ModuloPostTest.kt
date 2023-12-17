package com.demo.kiseong.yoo.demo.calculator

import com.demo.kiseong.yoo.demo.calculator.dto.CalculateResult
import com.demo.kiseong.yoo.demo.generateBody
import com.demo.kiseong.yoo.demo.generateRequestUrl
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClientException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ModuloPostTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("success on getting modulus of one number to another number")
    fun success() {
        val a = 7
        val b = 4
        val requestUrl = generateRequestUrl(port, "calculator", "modulo")
        val requestBody = generateBody(a, b)
        val response =
            testRestTemplate.postForEntity<CalculateResult<Int>>(requestUrl, requestBody)
        val body = response.body
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(a % b)
    }

    @Test
    @DisplayName("failure on getting modulus of one number to another number when 'a' is not integer")
    fun failureWhenRequestBodyANotInt() {
        val a = "ha ha"
        val b = 4
        val requestUrl = generateRequestUrl(port, "calculator", "modulo")
        val requestBody = generateBody(a, b)
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Int>>(requestUrl, requestBody)
        }
    }

    @Test
    @DisplayName("failure on getting modulus of one number to another number when 'b' is not integer")
    fun failureWhenRequestBodyBNotInt() {
        val a = 7
        val b = "ha ha"
        val requestUrl = generateRequestUrl(port, "calculator", "modulo")
        val requestBody = generateBody(a, b)
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Int>>(requestUrl, requestBody)
        }
    }
}
