package com.example.sooani.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BookCreateDto {

    @NotBlank(message = "제목은 공백이 아닌 문자로 작성해야 합니다.")
    private String title;

    @NotNull(message = "가격은 null일 수 없습니다.")
    @Positive(message = "가격은 0보다 큰 값이어야 합니다.")
    private Integer price;
}
