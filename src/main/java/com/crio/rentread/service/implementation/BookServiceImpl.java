package com.crio.rentread.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.BookResponse;
import com.crio.rentread.repository.BookStoreRepository;
import com.crio.rentread.service.BookService;

import jakarta.validation.Valid;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    public BookResponse createBook(@Valid BookRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBook'");
    }
}
