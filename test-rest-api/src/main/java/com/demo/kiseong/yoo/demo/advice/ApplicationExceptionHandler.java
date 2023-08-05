package com.demo.kiseong.yoo.demo.advice;

import com.demo.kiseong.yoo.demo.domain.calculator.core.validation.ParamValidateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Map<String, String>> handleMissingRequired(MissingServletRequestParameterException e) {
        final Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException e) {
        final Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(ResponseStatusException e) {
        final Map<String, String> errorMap = new HashMap<>();
        final String[] messageTokens = e.getMessage().split(" ", 3);
        final String message = messageTokens[2].substring(1, messageTokens[2].length() - 1);
        @Nonnull
        ParamValidateService.ErrorDivisorNotZero errorDivisorNotZero;
        try {
            errorDivisorNotZero = new ObjectMapper().readValue(message, ParamValidateService.ErrorDivisorNotZero.class);
        } catch (JsonProcessingException jsonProcessingException) {
            errorMap.put("message", "failed to parse invalid error message");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
        errorMap.put(errorDivisorNotZero.getField(), errorDivisorNotZero.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleUncheckedExceptions(Exception e) {
        final Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
