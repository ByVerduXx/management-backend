package com.ofc.management.controller;

import com.ofc.management.service.exception.InstrumentDoesNotExist;
import com.ofc.management.service.exception.UserDoesNotExist;
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

    @ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<Object> handleUserDoesNotExistException(UserDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InstrumentDoesNotExist.class)
    public ResponseEntity<Object> handleInstrumentDoesNotExistException(InstrumentDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
