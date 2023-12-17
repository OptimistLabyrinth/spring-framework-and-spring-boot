package com.demo.kiseong.yoo.demo.calculator

import com.demo.kiseong.yoo.demo.appendParams
import com.demo.kiseong.yoo.demo.calculator.dto.CalculateResult
import com.demo.kiseong.yoo.demo.generateRequestUrl
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClientException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MultiplyPostTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("success on multiplying two numbers")
    fun success() {
        val a = 2
        val b = 7
        val requestUrl = generateRequestUrl(port, "calculator", "multiply")
            .run { appendParams(this, a, b) }
        val response = testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        val body = response.body
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(a * b)
    }

    @Test
    @DisplayName("failure on multiplying two numbers when 'a' is not integer")
    fun failureWhenRequestBodyANotInt() {
        val a = "ha ha"
        val b = 7
        val requestUrl = generateRequestUrl(port, "calculator", "multiply")
        val requestBody: MutableMap<String, Any> = HashMap()
        requestBody["a"] = a
        requestBody["b"] = b
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        }
    }

    @Test
    @DisplayName("failure on multiplying two numbers when 'b' is not integer")
    fun failureWhenRequestBodyBNotInt() {
        val a = 2
        val b = "ha ha"
        val requestUrl = generateRequestUrl(port, "calculator", "multiply")
        val requestBody: MutableMap<String, Any> = HashMap()
        requestBody["a"] = a
        requestBody["b"] = b
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        }
    }
}
