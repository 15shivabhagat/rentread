package com.crio.rentread.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String author;
    private String genre;
    private Boolean available = true;
}
