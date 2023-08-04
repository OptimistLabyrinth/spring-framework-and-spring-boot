package com.demo.kiseong.yoo.demo.domain.calculator.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicArithmeticAllResult(
    @JsonProperty(required = true)
    Long plus,
    @JsonProperty(required = true)
    Long minus,
    @JsonProperty(required = true)
    Long multiply,
    @JsonProperty(required = true)
    Long divide,
    @JsonProperty(required = true)
    Long modulus
) {

}
