package com.demo.kiseong.yoo.demo.domain.calculator.api;

import com.demo.kiseong.yoo.demo.annotations.DefaultApiResponses;
import com.demo.kiseong.yoo.demo.domain.calculator.core.*;
import com.demo.kiseong.yoo.demo.domain.calculator.core.domain.BasicArithmeticDivideEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.domain.BasicArithmeticEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.dto.BasicArithmeticAllResult;
import com.demo.kiseong.yoo.demo.domain.calculator.core.validation.ParamValidateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final ParamValidateService paramValidateService;

    public CalculatorController(CalculatorService calculatorService, ParamValidateService paramValidateService) {
        this.calculatorService = calculatorService;
        this.paramValidateService = paramValidateService;
    }

    @GetMapping("plus")
    @DefaultApiResponses
    public final ResponseEntity<Long> plus(@RequestParam final long a, @RequestParam final long b) {
        return ResponseEntity.ok(calculatorService.plus(a, b));
    }

    @PostMapping("plus")
    @DefaultApiResponses
    public final ResponseEntity<Long> plus(@RequestBody @Valid final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.plus(body));
    }

    @GetMapping("minus")
    @DefaultApiResponses
    public final ResponseEntity<Long> minus(@RequestParam final long a, @RequestParam final long b) {
        return ResponseEntity.ok(calculatorService.minus(a, b));
    }

    @PostMapping("minus")
    @DefaultApiResponses
    public final ResponseEntity<Long> minus(@RequestBody @Valid final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.minus(body));
    }

    @GetMapping("multiply")
    @DefaultApiResponses
    public final ResponseEntity<Long> multiply(@RequestParam final long a, @RequestParam final long b) {
        return ResponseEntity.ok(calculatorService.multiply(a, b));
    }

    @PostMapping("multiply")
    @DefaultApiResponses
    public final ResponseEntity<Long> multiply(@RequestBody @Valid final BasicArithmeticEntity body) {
        return ResponseEntity.ok(calculatorService.multiply(body));
    }

    @GetMapping("divide")
    @DefaultApiResponses
    public final ResponseEntity<Long> divide(@RequestParam final long a, @RequestParam final long b) {
        paramValidateService.validate(ParamValidateService.ValidationType.divisorNotZero, "b", b);
        return ResponseEntity.ok(calculatorService.divide(a, b));
    }

    @PostMapping("divide")
    @DefaultApiResponses
    public final ResponseEntity<Long> divide(@RequestBody @Valid final BasicArithmeticDivideEntity body) {
        return ResponseEntity.ok(calculatorService.divide(body));
    }

    @GetMapping("modulus")
    @DefaultApiResponses
    public final ResponseEntity<Long> modulus(@RequestParam final long a, @RequestParam final long b) {
        paramValidateService.validate(ParamValidateService.ValidationType.divisorNotZero, "b", b);
        return ResponseEntity.ok(calculatorService.modulus(a, b));
    }

    @PostMapping("modulus")
    @DefaultApiResponses
    public final ResponseEntity<Long> modulus(@RequestBody @Valid final BasicArithmeticDivideEntity body) {
        return ResponseEntity.ok(calculatorService.modulus(body));
    }

    @GetMapping("basic-all")
    @DefaultApiResponses
    public final ResponseEntity<BasicArithmeticAllResult> basicAll(@RequestParam final long a, @RequestParam final long b) {
        paramValidateService.validate(ParamValidateService.ValidationType.divisorNotZero, "b", b);
        return ResponseEntity.ok(calculatorService.basicAll(a, b));
    }

    @PostMapping("basic-all")
    @DefaultApiResponses
    public final ResponseEntity<BasicArithmeticAllResult> basicAll(@RequestBody @Valid final BasicArithmeticDivideEntity body) {
        return ResponseEntity.ok(calculatorService.basicAll(body));
    }

}
