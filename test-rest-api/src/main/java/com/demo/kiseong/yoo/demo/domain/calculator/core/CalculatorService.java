package com.demo.kiseong.yoo.demo.domain.calculator.core;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public final Long plus(final Long a, final Long b) {
        return new BasicArithmeticEntity(a, b).plus();
    }

    public final Long plus(BasicArithmeticEntity body) {
        return body.plus();
    }

    public final Long minus(final Long a, final Long b) {
        return new BasicArithmeticEntity(a, b).minus();
    }

    public final Long minus(BasicArithmeticEntity body) {
        return body.minus();
    }

    public final Long multiply(final Long a, final Long b) {
        return new BasicArithmeticEntity(a, b).multiply();
    }

    public final Long multiply(BasicArithmeticEntity body) {
        return body.multiply();
    }

    public final Long divide(final Long a, final Long b) {
        return new BasicArithmeticEntity(a, b).divide();
    }

    public final Long divide(BasicArithmeticEntity body) {
        return body.divide();
    }

    public final Long modulus(final Long a, final Long b) {
        return new BasicArithmeticEntity(a, b).modulus();
    }

    public final Long modulus(BasicArithmeticEntity body) {
        return body.modulus();
    }

    public final BasicArithmeticAllResult basicAll(final Long a, final Long b) {
        return new BasicArithmeticAllResult(
            this.plus(a, b),
            this.minus(a, b),
            this.multiply(a, b),
            this.divide(a, b),
            this.modulus(a, b)
        );
    }

    public final BasicArithmeticAllResult basicAll(BasicArithmeticEntity body) {
        return new BasicArithmeticAllResult(
            body.plus(),
            body.minus(),
            body.multiply(),
            body.divide(),
            body.modulus()
        );
    }

}
