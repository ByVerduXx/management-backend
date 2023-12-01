package com.ofc.management.controller;

import com.ofc.management.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(ConcertDoesNotExist.class)
    public ResponseEntity<Object> handleConcertDoesNotExistException(ConcertDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(TitleCannotBeVoid.class)
    public ResponseEntity<Object> handleTitleCannotBeVoidException(TitleCannotBeVoid e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RehersalDoesNotExist.class)
    public ResponseEntity<Object> handleRehersalDoesNotExistException(RehersalDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDateProvided.class)
    public ResponseEntity<Object> handleNoDateProvidedException(NoDateProvided e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MusicianConcertDoesNotExist.class)
    public ResponseEntity<Object> handleMusicianConcertDoesNotExistException(MusicianConcertDoesNotExist e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VoidPasswordNotValid.class)
    public ResponseEntity<Object> handleVoidPasswordNotValidException(VoidPasswordNotValid e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminCannotDeleteHimself.class)
    public ResponseEntity<Object> handleAdminCannotDeleteHimselfException(AdminCannotDeleteHimself e){
        String err = e.getMessage();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
