package com.example.sooani.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class BookLogCreateDto {
    @Positive
    @NonNull
    private Integer bookId;

    @NonNull
    private String comment;

    private Integer page;
}
