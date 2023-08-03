package com.demo.kiseong.yoo.demo.domain.calculator.core;

public class BasicArithmeticEntity {

    private final Long a;
    private final Long b;

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
