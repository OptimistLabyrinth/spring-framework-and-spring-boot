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
class DivideGetTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("success on dividing one number by another number")
    fun success() {
        val a = 10.0
        val b = 5.0
        val requestUrl = generateRequestUrl(port, "calculator", "divide")
            .run { appendParams(this, a, b) }
        val response = testRestTemplate.getForEntity<CalculateResult<Double>>(requestUrl)
        val body = response.body
        AssertionsForClassTypes.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        AssertionsForClassTypes.assertThat(body?.result).isEqualTo(a / b)
    }

    @Test
    @DisplayName("failure on dividing one number by another number when 'a' is not integer")
    fun failureWhenRequestParamANotInt() {
        val a = "ha ha"
        val b = 5.0
        val requestUrl = "${generateRequestUrl(port, "calculator", "plus")}?a=$a&b=$b"
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Double>>(requestUrl)
        }
    }

    @Test
    @DisplayName("failure on dividing one number by another number when 'b' is not integer")
    fun failureWhenRequestParamBNotInt() {
        val a = 10.0
        val b = "ha ha"
        val requestUrl = "${generateRequestUrl(port, "calculator", "plus")}?a=$a&b=$b"
        assertThrows<RestClientException> {
            testRestTemplate.getForEntity<CalculateResult<Double>>(requestUrl)
        }
    }
}
