package com.crio.rentread.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
