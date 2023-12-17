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
class MinusGetTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("success on subtracting one number from another number")
    fun success() {
        val a = 5
        val b = 3
        val requestUrl = generateRequestUrl(port, "calculator", "minus")
            .run { appendParams(this, a, b) }
        val response = testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        val body = response.body
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(a - b)
    }

    @Test
    @DisplayName("failure on subtracting one number from another number when 'a' is not integer")
    fun failureWhenRequestParamANotInt() {
        val a = "ha ha"
        val b = 3
        val requestUrl = "${generateRequestUrl(port, "calculator", "minus")}?a=$a&b=$b"
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        }
    }

    @Test
    @DisplayName("failure on subtracting one number from another number when 'b' is not integer")
    fun failureWhenRequestParamBNotInt() {
        val a = 5
        val b = "ha ha"
        val requestUrl = "${generateRequestUrl(port, "calculator", "minus")}?a=$a&b=$b"
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Int>>(requestUrl)
        }
    }
}
