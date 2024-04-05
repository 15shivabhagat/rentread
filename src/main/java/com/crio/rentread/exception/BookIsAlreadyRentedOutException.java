package com.crio.rentread.exception;

public class BookIsAlreadyRentedOutException extends Exception {
    public BookIsAlreadyRentedOutException(String msg) {
        super(msg);
    }
}
