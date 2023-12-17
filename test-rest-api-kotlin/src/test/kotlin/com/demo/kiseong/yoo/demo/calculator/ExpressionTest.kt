package com.demo.kiseong.yoo.demo.calculator

import com.demo.kiseong.yoo.demo.calculator.dto.CalculateResult
import com.demo.kiseong.yoo.demo.calculator.dto.ExpressionBody
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
class ExpressionTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("1 + 1 = 2")
    fun success1() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 + 1")
        val response = testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        val body = response.body
        val expected = (1 + 1).toDouble()
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(expected)
    }

    @Test
    @DisplayName("1 + 1 * 3 = 4")
    fun success2() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 + 1 * 3")
        val response = testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        val body = response.body
        val expected = (1 + 1 * 3).toDouble()
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(expected)
    }

    @Test
    @DisplayName("(1 + 1) * 3 = 6")
    fun success3() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("(1 + 1) * 3")
        val response = testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        val body = response.body
        val expected = ((1 + 1) * 3).toDouble()
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(expected)
    }

    @Test
    @DisplayName("1 + 1 * 3 + 2 * 5 - 8 / 2 = 10")
    fun success4() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 + 1 * 3 + 2 * 5 - 8 / 2")
        val response = testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        val body = response.body
        val expected = (1 + 1 * 3 + 2 * 5 - 8 / 2).toDouble()
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(expected)
    }

    @Test
    @DisplayName("1 + (2 + 3 * (4 + 5 % 3)) + (5 * (3 - 4)) = ")
    fun success5() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 + (2 + 3 * (4 + 5 % 3)) + (5 * (3 - 4))")
        val response = testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        val body = response.body
        val expected = (1 + (2 + 3 * (4 + 5 % 3)) + (5 * (3 - 4))).toDouble()
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(expected)
    }

    @Test
    @DisplayName("failure when expression is empty string")
    fun failure1() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("")
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        }
    }

    @Test
    @DisplayName("failure when expression contains non-numeric string")
    fun failure2() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 + a")
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        }
    }

    @Test
    @DisplayName("failure when expression does not contains at least one operator")
    fun failure3() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("10")
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        }
    }

    @Test
    @DisplayName("failure when expression does not end with operand at the end")
    fun failure4() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("1 +")
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        }
    }

    @Test
    @DisplayName("failure when parenthesis does not properly closed in expression")
    fun failure5() {
        val requestUrl = generateRequestUrl(port, "calculator", "expression")
        val requestBody = ExpressionBody("2 * (3 + 4")
        assertThrows<RestClientException> {
            testRestTemplate.postForEntity<CalculateResult<Double>>(requestUrl, requestBody)
        }
    }
}
