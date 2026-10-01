package com.swagger.coderestapi.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientCreditException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public Map<String, String> handleInsufficientCredit(
            InsufficientCreditException exception) {

        return Map.of(
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFound(
            OrderNotFoundException exception) {

        return Map.of(
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler(PartnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePartnerNotFound(
            PartnerNotFoundException exception) {

        return Map.of(
                "message", exception.getMessage()
        );
    }
}
