package com.demo.kiseong.yoo.demo.calculator

import com.demo.kiseong.yoo.demo.calculator.dto.CalculateBody
import com.demo.kiseong.yoo.demo.calculator.dto.CalculateResult
import com.demo.kiseong.yoo.demo.calculator.dto.ExpressionBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("calculator")
class CalculatorController(private val calculatorService: CalculatorService) {

    @GetMapping("plus")
    fun plusParam(@RequestParam a: Int, @RequestParam b: Int): CalculateResult<Int> {
        return CalculateResult(a + b)
    }

    @PostMapping("plus")
    fun plusBody(@RequestBody body: CalculateBody<Int>): CalculateResult<Int> {
        return CalculateResult(body.a + body.b)
    }

    @GetMapping("minus")
    fun minusParam(@RequestParam a: Int, @RequestParam b: Int): CalculateResult<Int> {
        return CalculateResult(a - b)
    }

    @PostMapping("minus")
    fun minusBody(@RequestBody body: CalculateBody<Int>): CalculateResult<Int> {
        return CalculateResult(body.a - body.b)
    }

    @GetMapping("multiply")
    fun multiplyParam(@RequestParam a: Int, @RequestParam b: Int): CalculateResult<Int> {
        return CalculateResult(a * b)
    }

    @PostMapping("multiply")
    fun multiplyBody(@RequestBody body: CalculateBody<Int>): CalculateResult<Int> {
        return CalculateResult(body.a * body.b)
    }

    @GetMapping("divide")
    fun divideParam(@RequestParam a: Double, @RequestParam b: Double): CalculateResult<Double> {
        return CalculateResult(a / b)
    }

    @PostMapping("divide")
    fun divideBody(@RequestBody body: CalculateBody<Double>): CalculateResult<Double> {
        return CalculateResult(body.a / body.b)
    }

    @GetMapping("modulo")
    fun moduloParam(@RequestParam a: Int, @RequestParam b: Int): CalculateResult<Int> {
        return CalculateResult(a % b)
    }

    @PostMapping("modulo")
    fun moduloBody(@RequestBody body: CalculateBody<Int>): CalculateResult<Int> {
        return CalculateResult(body.a % body.b)
    }

    @PostMapping("expression")
    fun expression(@RequestBody body: ExpressionBody): CalculateResult<Double> {
        val result = calculatorService.execute(body.expression)
        return CalculateResult(result)
    }

}
