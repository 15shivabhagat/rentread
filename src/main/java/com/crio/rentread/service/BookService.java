package com.crio.rentread.service;

import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.BookResponse;

import jakarta.validation.Valid;

public interface BookService {

    BookResponse createBook(@Valid BookRequest request);
    
}
