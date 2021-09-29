package ru.job4j.forum.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostGlobalExceptionHandler {

    @ExceptionHandler(NoSuchPostException.class)
    protected ResponseEntity<Object> handleNoSuchPostException(NoSuchPostException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
