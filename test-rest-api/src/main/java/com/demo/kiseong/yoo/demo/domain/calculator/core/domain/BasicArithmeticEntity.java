package com.demo.kiseong.yoo.demo.domain.calculator.core.domain;

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
public class BasicArithmeticEntity {

    @JsonProperty(required = true)
    @NotNull
    private Long a;

    @JsonProperty(required = true)
    @NotNull
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

}
