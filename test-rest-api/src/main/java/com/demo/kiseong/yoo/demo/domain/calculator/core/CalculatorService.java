package com.demo.kiseong.yoo.demo.domain.calculator.core;

import com.demo.kiseong.yoo.demo.domain.calculator.core.domain.BasicArithmeticDivideEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.domain.BasicArithmeticEntity;
import com.demo.kiseong.yoo.demo.domain.calculator.core.dto.BasicArithmeticAllResult;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public final long plus(final long a, final long b) {
        return new BasicArithmeticEntity(a, b).plus();
    }

    public final long plus(BasicArithmeticEntity body) {
        return body.plus();
    }

    public final long minus(final long a, final long b) {
        return new BasicArithmeticEntity(a, b).minus();
    }

    public final long minus(BasicArithmeticEntity body) {
        return body.minus();
    }

    public final long multiply(final long a, final long b) {
        return new BasicArithmeticEntity(a, b).multiply();
    }

    public final long multiply(BasicArithmeticEntity body) {
        return body.multiply();
    }

    public final long divide(final long a, final long b) {
        return new BasicArithmeticDivideEntity(a, b).divide();
    }

    public final long divide(BasicArithmeticDivideEntity body) {
        return body.divide();
    }

    public final long modulus(final long a, final long b) {
        return new BasicArithmeticDivideEntity(a, b).modulus();
    }

    public final long modulus(BasicArithmeticDivideEntity body) {
        return body.modulus();
    }

    public final BasicArithmeticAllResult basicAll(final long a, final long b) {
        return new BasicArithmeticAllResult(
            this.plus(a, b),
            this.minus(a, b),
            this.multiply(a, b),
            this.divide(a, b),
            this.modulus(a, b)
        );
    }

    public final BasicArithmeticAllResult basicAll(BasicArithmeticDivideEntity body) {
        return new BasicArithmeticAllResult(
            body.plus(),
            body.minus(),
            body.multiply(),
            body.divide(),
            body.modulus()
        );
    }

}
