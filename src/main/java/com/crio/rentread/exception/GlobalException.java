package com.crio.rentread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crio.rentread.exchange.response.Response;

@RestControllerAdvice
public class GlobalException {
    
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> userAlreadyExistException(UserAlreadyExistException e) {
        Response response = new Response(e.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Response> bookNotFoundException(BookNotFoundException e) {
        Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
