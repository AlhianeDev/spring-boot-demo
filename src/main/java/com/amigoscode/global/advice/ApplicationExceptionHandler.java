package com.amigoscode.global.advice;

import com.amigoscode.global.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgs(MethodArgumentNotValidException ex) {

        Map<String, String> errorsMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            errorsMap.put(error.getField(), error.getDefaultMessage());

        });

        return errorsMap;

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String, String> handleNotFoundException(CustomerNotFoundException ex) {

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("Error Message", ex.getMessage());

        return errorMap;

    }

}
