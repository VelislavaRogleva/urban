package app.urbanist.controller;

import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(EmailNotUniqueException.class)
    public ResponseEntity<?> handleException(EmailNotUniqueException e) {
        return new  ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotUniqueException.class)
    public ResponseEntity<?> handleException(UsernameNotUniqueException e) {
        return new  ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
