package com.crio.rentread.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentread.entity.BookStore;
import com.crio.rentread.entity.User;
import com.crio.rentread.exception.BookIsAlreadyRentedOutException;
import com.crio.rentread.exception.BookNotFoundException;
import com.crio.rentread.exception.NotAbleToRentException;
import com.crio.rentread.exception.UserNotFoundException;
import com.crio.rentread.exchange.request.RentBookRequest;
import com.crio.rentread.exchange.response.RentBookResponse;
import com.crio.rentread.repository.BookStoreRepository;
import com.crio.rentread.repository.UserRepository;
import com.crio.rentread.service.RentalManagementService;

@Service
public class RentalManagementServiceImpl implements RentalManagementService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RentBookResponse rentBook(Long id, RentBookRequest request) throws BookNotFoundException,
            BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException {
        BookStore book = bookStoreRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book is not present"));
        User user = getUserWithRentedBooks(request.getUserId());
        if (book.getAvailable() == false) {
            throw new BookIsAlreadyRentedOutException("Book is already rented out");
        }
        if (user.getBooks().size() >= 2) {
            throw new NotAbleToRentException("Not able to rent book, you already rented 2 books");
        }
        book.setAvailable(false);
        List<BookStore> books = user.getBooks();
        books.add(book);
        book.setUser(user);
        user.setBooks(books);
        userRepository.save(user);
        return RentBookResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .books(user.getBooks())
                .role(user.getRole())
                .build();

    }

    @Override
    public RentBookResponse returnBook(Long id, RentBookRequest request)
            throws UserNotFoundException, BookNotFoundException {
        User user = getUserWithRentedBooks(request.getUserId());
        BookStore book = bookStoreRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book is not present"));
        List<BookStore> books = user.getBooks();
        if (!books.contains(book)) {
            throw new BookNotFoundException("Book is not present");
        }
        book.setAvailable(true);
        book.setUser(null);
        books.remove(book);
        user.setBooks(books);
        userRepository.save(user);
        return RentBookResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .books(user.getBooks())
                .role(user.getRole())
                .build();
    }

    private User getUserWithRentedBooks(Long id) throws UserNotFoundException {
        return userRepository.findUserWithRentedBooksById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not present"));
    }

}
