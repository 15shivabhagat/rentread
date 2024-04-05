package com.crio.rentread.service;

import com.crio.rentread.exception.BookIsAlreadyRentedOutException;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exception.NotAbleToRentException;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.exchange.request.RentBookRequest;
import com.crio.rentread.exchange.response.RentBookResponse;

public interface RentalManagementService {

    RentBookResponse rentBook(Long id, RentBookRequest request) throws BookNotFoundException, BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException;

    RentBookResponse returnBook(Long id, RentBookRequest request) throws UserNotFoundException, BookNotFoundException;
    
}
