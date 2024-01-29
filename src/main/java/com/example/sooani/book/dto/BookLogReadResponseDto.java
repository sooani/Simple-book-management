package com.example.sooani.book.dto;

import com.example.sooani.book.entity.BookLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookLogReadResponseDto {
    private Integer bookLogId;
    private String comment;
    private Integer page;
    private LocalDateTime insertDateTime;

    private String displayComment;//책 기록을 보여주기위한 가상의 필드

    public BookLogReadResponseDto fromBookLog(BookLog bookLog) {
        this.bookLogId = bookLog.getBookLogId();
        this.comment = bookLog.getComment();
        this.page = bookLog.getPage();
        this.insertDateTime = bookLog.getInsertDateTime();

        this.displayComment = (this.page == null ? "" : "(p." + String.valueOf(this.page) + ".) ") + this.comment;
        return this;
    }

    public static BookLogReadResponseDto BookLogFactory(BookLog bookLog) {
        BookLogReadResponseDto bookLogReadResponseDto = new BookLogReadResponseDto();
        bookLogReadResponseDto.fromBookLog(bookLog);
        return bookLogReadResponseDto;
    }
}
