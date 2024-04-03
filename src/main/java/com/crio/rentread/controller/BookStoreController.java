package com.crio.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentread.entity.BookStore;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookStoreController {
    private final String URL = "/api";

    @Autowired
    private BookService bookService;

    @PostMapping(URL+"/books")
    public ResponseEntity<BookStore> createBook(@Valid @RequestBody BookRequest request) {
        BookStore response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(URL+"/books/{id}")
    public ResponseEntity<BookStore> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest request) throws BookNotFoundException {
        BookStore response = bookService.updateBook(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(URL+"/books/{id}")
    public ResponseEntity<Response> removeBook(@PathVariable("id") Long id) throws BookNotFoundException {
        Response response = bookService.removeBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
