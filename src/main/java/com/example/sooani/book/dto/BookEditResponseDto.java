package com.example.sooani.book.dto;

import com.example.sooani.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookEditResponseDto {
    private Integer bookId;

    private String title;

    private Integer price;

    private LocalDateTime insertDateTime;

    public BookEditResponseDto fromBook(Book book) {
        //Book엔티티로부터 필요한 정보를 가져와서 해당 필드에 설정한다
        //이 메서드는 this를 반환해서 메서드를 호출한 객체자체를 반환한다

        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getInsertDateTime();

        return this;
    }

    public static BookEditResponseDto BookFactory(Book book) {
        BookEditResponseDto bookEditResponseDto = new BookEditResponseDto();
        bookEditResponseDto.fromBook(book);
        return bookEditResponseDto;
    }
}
