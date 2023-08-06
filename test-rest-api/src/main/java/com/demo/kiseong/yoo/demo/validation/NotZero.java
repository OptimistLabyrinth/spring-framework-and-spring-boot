package com.demo.kiseong.yoo.demo.validation;

import com.demo.kiseong.yoo.demo.domain.calculator.core.validation.ParamValidateService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotZero.NotZeroValidator.class)
@Documented
public @interface NotZero {

    String message() default ParamValidateService.divisorNotZeroMessage;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotZeroValidator implements ConstraintValidator<NotZero, Long> {

        private String message;

        @Override
        public void initialize(NotZero constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
            message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context) {
            if (value != null && value == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                return false;
            }
            return true;
        }

    }

}
