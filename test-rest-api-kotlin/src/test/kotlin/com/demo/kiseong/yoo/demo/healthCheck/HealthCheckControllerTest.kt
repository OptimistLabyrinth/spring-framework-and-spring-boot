package com.demo.kiseong.yoo.demo.healthCheck

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckControllerTest(
    @Autowired
    private val testRestTemplate: TestRestTemplate,
    @LocalServerPort
    private var port: Int,
) {
    @Test
    @DisplayName("health check OK")
    fun statusOk() {
        val requestUrl = "http://localhost:$port/health-check"
        val responseEntity = testRestTemplate.getForEntity(requestUrl, HealthCheckController.HealthCheck::class.java)
        val body = responseEntity.body
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(body?.status ?: "").isEqualTo("OK")
    }
}
