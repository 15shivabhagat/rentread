package com.crio.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.exception.UserAlreadyExistException;
import com.crio.rentread.exchange.request.LoginUserRequest;
import com.crio.rentread.exchange.request.RegisterUserRequest;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {
    private final String URL = "/api";

    @Autowired
    private AuthService authservice;

    @PostMapping(URL+"/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterUserRequest request) throws UserAlreadyExistException {
        Response response = authservice.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(URL+"/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginUserRequest request) {
        Response response = authservice.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
