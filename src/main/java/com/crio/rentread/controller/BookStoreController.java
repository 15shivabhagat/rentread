package com.crio.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.BookResponse;
import com.crio.rentread.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookStoreController {
    private final String URL = "/api";

    @Autowired
    private BookService bookService;

    @PostMapping(URL+"/books")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
