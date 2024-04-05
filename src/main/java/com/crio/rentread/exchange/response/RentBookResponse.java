package com.crio.rentread.exchange.response;

import java.util.List;

import com.crio.rentread.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.crio.rentread.entity.BookStore;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentBookResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private List<BookStore> books;
}
