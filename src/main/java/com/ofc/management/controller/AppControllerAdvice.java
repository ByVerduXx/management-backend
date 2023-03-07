package com.ofc.management.controller;

import com.ofc.management.service.exception.UsernameAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExists e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}
