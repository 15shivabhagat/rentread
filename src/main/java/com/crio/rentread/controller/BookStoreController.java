package com.crio.rentread.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookStoreController {
    private final String URL = "/api";

    @Autowired
    private BookService bookService;

    @PostMapping(URL + "/books")
    public ResponseEntity<BookStore> createBook(@Valid @RequestBody BookRequest request) {
        log.info("Received POST request for create book with book: {}", request.toString());
        BookStore response = bookService.createBook(request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(URL + "/books/{id}")
    public ResponseEntity<BookStore> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest request)
            throws BookNotFoundException {
        log.info("Received PUT request for update book with book id: {}", id);
        BookStore response = bookService.updateBook(id, request);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(URL + "/books/{id}")
    public ResponseEntity<Response> removeBook(@PathVariable("id") Long id) throws BookNotFoundException {
        log.info("Received DELETE request for delete book with book id: {}", id);
        Response response = bookService.removeBook(id);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(URL + "/books")
    public ResponseEntity<List<BookStore>> getAllBooks() {
        log.info("Received GET request for books: {}");
        List<BookStore> books = bookService.getAllBooks();
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping(URL + "/books/{id}")
    public ResponseEntity<BookStore> getBookById(@PathVariable("id") Long id) throws BookNotFoundException {
        log.info("Received GET request for books with book id: {}", id);
        BookStore book = bookService.getBookById(id);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
}
