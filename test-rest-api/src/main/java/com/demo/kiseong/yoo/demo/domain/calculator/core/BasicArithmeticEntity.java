package com.demo.kiseong.yoo.demo.domain.calculator.core;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class BasicArithmeticEntity {

    private Long a;
    private Long b;

    public BasicArithmeticEntity(final Long a, final Long b) {
        this.a = a;
        this.b = b;
    }

    public final Long plus() {
        return a + b;
    }

    public final Long minus() {
        return a - b;
    }

    public final Long multiply() {
        return a * b;
    }

    public final Long divide() {
        return a / b;
    }

    public final Long modulus() {
        return a % b;
    }

}
