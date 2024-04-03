package com.crio.rentread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.rentread.entity.BookStore;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
    
}
