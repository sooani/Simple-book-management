package com.example.sooani.book.dto;

import lombok.Getter;

@Getter
public class BookListResponseDto {
    private Integer bookId;

    private String title;

    public BookListResponseDto(Integer bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }
}
