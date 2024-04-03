package com.crio.rentread.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.rentread.entity.User;
import com.crio.rentread.entity.enums.Role;
import com.crio.rentread.exception.UserAlreadyExistException;
import com.crio.rentread.exchange.request.LoginUserRequest;
import com.crio.rentread.exchange.request.RegisterUserRequest;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.repository.UserRepository;
import com.crio.rentread.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response registerUser(RegisterUserRequest request) throws UserAlreadyExistException {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("User is already register");
        }
        if(request.getRole() == null) {
            request.setRole(Role.USER);
        }
        
        User user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(request.getRole())
                        .build();
        
        userRepository.save(user);
        return new Response("User Registered Successfully!", HttpStatus.CREATED);
    }

    @Override
    public Response login(LoginUserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new Response("User Logged in successfully!", HttpStatus.OK);
    }
    
}
