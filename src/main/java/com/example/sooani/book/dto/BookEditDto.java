package com.example.sooani.book.dto;

import com.example.sooani.book.entity.Book;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BookEditDto {
    @NonNull
    @Positive
    private Integer bookId;

    @NonNull
    @NotBlank(message = "책 이름에 공백은 사용할 수 없습니다.")
    private String title;

    @NonNull
    @Min(value = 1000, message = "금액은 1000원 이상으로 입력해주세요.")
    private Integer price;

    public Book fill(Book book) {
        book.setTitle(this.title);
        book.setPrice(this.price);
        return book;
    }
}
