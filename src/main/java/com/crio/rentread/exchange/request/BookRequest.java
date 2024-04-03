package com.crio.rentread.exchange.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String genre;
    private Boolean available = true;
}
