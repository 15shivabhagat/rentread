package com.crio.rentread.service;

import com.crio.rentread.entity.BookStore;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.Response;

import jakarta.validation.Valid;

public interface BookService {

    BookStore createBook(BookRequest request);

    BookStore updateBook(Long id, BookRequest request) throws BookNotFoundException;

    Response removeBook(Long id) throws BookNotFoundException;
    
}
