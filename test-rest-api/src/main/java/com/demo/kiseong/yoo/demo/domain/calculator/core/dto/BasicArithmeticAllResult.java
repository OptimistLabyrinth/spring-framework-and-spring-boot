package com.demo.kiseong.yoo.demo.domain.calculator.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicArithmeticAllResult(
    @JsonProperty(required = true)
    long plus,
    @JsonProperty(required = true)
    long minus,
    @JsonProperty(required = true)
    long multiply,
    @JsonProperty(required = true)
    long divide,
    @JsonProperty(required = true)
    long modulus
) {

}
