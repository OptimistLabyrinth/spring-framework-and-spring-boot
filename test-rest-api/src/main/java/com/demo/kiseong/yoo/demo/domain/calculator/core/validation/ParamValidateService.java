package com.demo.kiseong.yoo.demo.domain.calculator.core.validation;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ParamValidateService {

    @RequiredArgsConstructor
    @Getter
    public enum ValidationType {
        divisorNotZero(1);

        private final int value;
    }

    public static final String divisorNotZeroMessage = "divisor must not be zero";

    @SuppressWarnings({"SwitchStatementWithTooFewBranches", "EnhancedSwitchMigration", "UnusedReturnValue"})
    @SneakyThrows
    public ParamValidateService validate(ValidationType validationType, final String fieldName, final long value) {
        switch (validationType) {
            case divisorNotZero:
                divisorNotZero(fieldName, value);
                break;
            default:
                throw new Exception("invalid validation type");
        }
        return this;
    }

    public void divisorNotZero(final String fieldName, final long value) {
        if (value == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, new ErrorDivisorNotZero(fieldName, divisorNotZeroMessage).toString());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ErrorDivisorNotZero {

        private String field;
        private String message;

        @Override
        public String toString() {
            return "{ \"field\": \"" + field + "\", \"message\": \"" + message + "\" }";
        }

    }

}
