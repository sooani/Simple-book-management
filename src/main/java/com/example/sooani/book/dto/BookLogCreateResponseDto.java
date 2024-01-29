package com.example.sooani.book.dto;

import com.example.sooani.book.entity.BookLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookLogCreateResponseDto {
    private Integer bookLogId;
    private Integer bookId;
    private String comment;
    private Integer page;

    public BookLogCreateResponseDto fromBookLog(BookLog bookLog) {
        this.bookLogId = bookLog.getBookLogId();
        this.bookId = bookLog.getBook().getBookId();
        this.comment = bookLog.getComment();
        this.page = bookLog.getPage();
        return this;
    }

    public static BookLogCreateResponseDto BookLogFactory(BookLog bookLog) {
        BookLogCreateResponseDto bookLogCreateResponseDto = new BookLogCreateResponseDto();

        bookLogCreateResponseDto.fromBookLog(bookLog);
        return bookLogCreateResponseDto;
    }
}
