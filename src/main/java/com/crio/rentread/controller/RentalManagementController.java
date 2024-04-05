package com.crio.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.exception.BookIsAlreadyRentedOutException;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exception.NotAbleToRentException;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.exchange.request.RentBookRequest;
import com.crio.rentread.exchange.response.RentBookResponse;
import com.crio.rentread.service.RentalManagementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RentalManagementController {

    private final String URL = "/api";

    @Autowired
    private RentalManagementService rentalManagementService;

    @PostMapping(URL + "/books/{id}/rent")
    public ResponseEntity<RentBookResponse> rentBook(@PathVariable("id") Long id, @RequestBody RentBookRequest request)
            throws BookNotFoundException, UserNotFoundException, BookIsAlreadyRentedOutException,
            NotAbleToRentException {
        log.info("Received POST request for rent a book id: {}", id);
        RentBookResponse response = rentalManagementService.rentBook(id, request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(URL + "/books/{id}/return")
    public ResponseEntity<RentBookResponse> returnBook(@PathVariable("id") Long id,
            @RequestBody RentBookRequest request) throws UserNotFoundException, BookNotFoundException {
        log.info("Received POST request for return a book id: {}", id);
        RentBookResponse response = rentalManagementService.returnBook(id, request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
