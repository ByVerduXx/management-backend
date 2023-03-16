package com.ofc.management.controller;

import com.ofc.management.service.exception.*;
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

    @ExceptionHandler(OldPasswordDoesNotMatch.class)
    public ResponseEntity<Object> handleOldPasswordDoesNotMatchException(OldPasswordDoesNotMatch e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AnnouncementDoesNotExist.class)
    public ResponseEntity<Object> handleAnnouncementDoesNotExistException(AnnouncementDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
