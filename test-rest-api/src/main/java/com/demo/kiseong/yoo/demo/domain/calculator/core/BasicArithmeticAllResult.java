package com.demo.kiseong.yoo.demo.domain.calculator.core;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ResponseBody
public class BasicArithmeticAllResult {

    private final Long plus;
    private final Long minus;
    private final Long multiply;
    private final Long divide;
    private final Long modulus;

    public BasicArithmeticAllResult(
        final Long plus,
        final Long minus,
        final Long multiply,
        final Long divide,
        final Long modulus
    ) {
        this.plus = plus;
        this.minus = minus;
        this.multiply = multiply;
        this.divide = divide;
        this.modulus = modulus;
    }

    public final Map<String, Long> toJson() {
        return Map.of(
            "plus", plus,
            "minus", minus,
            "multiply", multiply,
            "divide", divide,
            "modulus", modulus
        );
    }

}
