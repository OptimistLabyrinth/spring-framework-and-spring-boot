package com.demo.kiseong.yoo.demo.healthCheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("health-check")
class HealthCheckController {

    @GetMapping
    fun check(): HealthCheck {
        return HealthCheck()
    }

    data class HealthCheck(val status: String = "OK")

}
