package com.demo.kiseong.yoo.demo.domain.calculator.api;

import com.demo.kiseong.yoo.demo.domain.calculator.core.BasicArithmeticAllResult;
import com.demo.kiseong.yoo.demo.domain.calculator.core.BasicArithmeticEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("plus")
    public final ResponseEntity<Long> plus(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.plus(a, b));
    }

    @PostMapping("plus")
    public final ResponseEntity<Long> plus(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.plus(body));
    }

    @GetMapping("minus")
    public final ResponseEntity<Long> minus(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.minus(a, b));
    }

    @PostMapping("minus")
    public final ResponseEntity<Long> minus(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.minus(body));
    }

    @GetMapping("multiply")
    public final ResponseEntity<Long> multiply(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.multiply(a, b));
    }

    @PostMapping("multiply")
    public final ResponseEntity<Long> multiply(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.multiply(body));
    }

    @GetMapping("divide")
    public final ResponseEntity<Long> divide(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.divide(a, b));
    }

    @PostMapping("divide")
    public final ResponseEntity<Long> divide(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.divide(body));
    }

    @GetMapping("modulus")
    public final ResponseEntity<Long> modulus(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.modulus(a, b));
    }

    @PostMapping("modulus")
    public final ResponseEntity<Long> modulus(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.modulus(body));
    }

    @GetMapping("basic-all")
    public final ResponseEntity<BasicArithmeticAllResult> basicAll(@RequestParam final Long a, @RequestParam final Long b) {
        return ResponseEntity.ok(calculatorService.basicAll(a, b));
    }

    @PostMapping("basic-all")
    public final ResponseEntity<BasicArithmeticAllResult> basicAll(@RequestBody final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.basicAll(body));
    }

}
