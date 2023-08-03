package com.demo.kiseong.yoo.demo.domain.calculator.api;

import com.demo.kiseong.yoo.demo.domain.calculator.core.BasicArithmeticEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.CalculatorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("plus")
    public final Long plus(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.plus(a, b);
    }

    @PostMapping(value = "plus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long plus(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.plus(body);
    }

    @GetMapping("minus")
    public final Long minus(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.minus(a, b);
    }

    @PostMapping(value = "minus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long minus(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.minus(body);
    }

    @GetMapping("multiply")
    public final Long multiply(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.multiply(a, b);
    }

    @PostMapping(value = "multiply", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long multiply(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.multiply(body);
    }

    @GetMapping("divide")
    public final Long divide(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.divide(a, b);
    }

    @PostMapping(value = "divide", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long divide(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.divide(body);
    }

    @GetMapping("modulus")
    public final Long modulus(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.modulus(a, b);
    }

    @PostMapping(value = "modulus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final Long modulus(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.modulus(body);
    }

    @GetMapping(value = "basic-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, Long> basicAll(@RequestParam final Long a, @RequestParam final Long b) {
        return calculatorService.basicAll(a, b).toJson();
    }

    @PostMapping(
        value = "basic-all",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public final Map<String, Long> basicAll(@RequestBody final BasicArithmeticEntity body) {
        return calculatorService.basicAll(body).toJson();
    }

}
