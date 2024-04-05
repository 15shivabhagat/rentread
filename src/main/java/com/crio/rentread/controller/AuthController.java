package com.crio.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.exception.UserAlreadyExistException;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.exchange.request.LoginUserRequest;
import com.crio.rentread.exchange.request.RegisterUserRequest;
import com.crio.rentread.exchange.response.RentBookResponse;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Slf4j
public class AuthController {
    private final String URL = "/api";

    @Autowired
    private AuthService authservice;

    @PostMapping(URL + "/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterUserRequest request)
            throws UserAlreadyExistException {
        log.info("Received POST request for regidter with email: {}", request.getEmail());
        Response response = authservice.registerUser(request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(URL + "/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginUserRequest request) {
        log.info("Received POST request for login with email: {}", request.getEmail());
        Response response = authservice.login(request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(URL + "/users/{id}")
    public ResponseEntity<RentBookResponse> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        log.info("Received GET request for user with id: {}", id);
        RentBookResponse response = authservice.getUserById(id);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
