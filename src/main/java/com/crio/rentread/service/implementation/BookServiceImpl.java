package com.crio.rentread.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.crio.rentread.entity.BookStore;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exchange.request.BookRequest;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.repository.BookStoreRepository;
import com.crio.rentread.service.BookService;

import jakarta.validation.Valid;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    public BookStore createBook(@Valid BookRequest request) {
        if(bookStoreRepository.existsByTitle(request.getTitle())) {
            return null;
        }

        BookStore newBook = BookStore.builder()
                                    .title(request.getTitle())
                                    .author(request.getAuthor())
                                    .genre(request.getGenre())
                                    .available(request.getAvailable())
                                    .build();
        BookStore book = bookStoreRepository.save(newBook);
        return book;
    }

    @Override
    public BookStore updateBook(Long id, BookRequest request) throws BookNotFoundException {
        BookStore book = bookStoreRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not present"));
        if(request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if(request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if(request.getGenre() != null) {
            book.setGenre(request.getGenre());
        }
        return bookStoreRepository.save(book);
    }

    @Override
    public Response removeBook(Long id) throws BookNotFoundException {
        BookStore book = bookStoreRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book is not present"));
        bookStoreRepository.delete(book);
        return new Response("Book removed successfully!", HttpStatus.OK);
    }
}
