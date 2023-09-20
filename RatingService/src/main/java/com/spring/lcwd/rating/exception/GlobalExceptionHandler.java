package com.spring.lcwd.rating.exception;

import com.spring.lcwd.rating.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RatingException.class)
    public ResponseEntity<ErrorDetails> ratingExceptionHandler(RatingException exception, WebRequest request) {

        return new ResponseEntity<>(new ErrorDetails(exception.getMessage(), request.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}