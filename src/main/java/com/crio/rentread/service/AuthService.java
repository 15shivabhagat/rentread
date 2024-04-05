package com.crio.rentread.service;

import com.crio.rentread.exception.UserAlreadyExistException;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.exchange.request.LoginUserRequest;
import com.crio.rentread.exchange.request.RegisterUserRequest;
import com.crio.rentread.exchange.response.RentBookResponse;
import com.crio.rentread.exchange.response.Response;


public interface AuthService {
    public Response registerUser(RegisterUserRequest request) throws UserAlreadyExistException;

    public Response login(LoginUserRequest request);

    public RentBookResponse getUserById(Long id) throws UserNotFoundException;
}
