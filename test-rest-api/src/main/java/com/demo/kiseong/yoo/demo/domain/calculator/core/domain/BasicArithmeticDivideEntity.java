package com.demo.kiseong.yoo.demo.domain.calculator.core.domain;

import com.demo.kiseong.yoo.demo.validation.NotZero;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasicArithmeticDivideEntity {

    @JsonProperty(required = true)
    @NotNull
    private Long a;

    @JsonProperty(required = true)
    @NotNull
    @NotZero
    private Long b;

    public long plus() {
        return a + b;
    }

    public long minus() {
        return a - b;
    }

    public long multiply() {
        return a * b;
    }

    public long divide() {
        return a / b;
    }

    public long modulus() {
        return a % b;
    }

}
