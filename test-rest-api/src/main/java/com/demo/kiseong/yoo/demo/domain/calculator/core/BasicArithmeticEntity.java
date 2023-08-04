package com.demo.kiseong.yoo.demo.domain.calculator.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicArithmeticEntity(
    @JsonProperty(required = true)
    Long a,
    @JsonProperty(required = true)
    Long b
) {

    public Long plus() {
        return a + b;
    }

    public Long minus() {
        return a - b;
    }

    public Long multiply() {
        return a * b;
    }

    public Long divide() {
        return a / b;
    }

    public Long modulus() {
        return a % b;
    }

}
