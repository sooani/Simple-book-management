package com.example.sooani.book.controller;

import com.example.sooani.book.dto.BookLogCreateDto;
import com.example.sooani.book.dto.BookLogCreateResponseDto;
import com.example.sooani.book.service.BookLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//JSON 객체를 입력받고 응답
@RequestMapping("/book-log")
public class BookLogController {
    private BookLogService bookLogService;

    @Autowired //의존성주입
    public void setBookLogService(BookLogService bookLogService) {
        this.bookLogService = bookLogService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookLogCreateResponseDto> insert(@RequestBody BookLogCreateDto bookLogCreateDto) {
        BookLogCreateResponseDto bookLogCreateResponseDto = this.bookLogService.insert(bookLogCreateDto);
        return ResponseEntity.ok(bookLogCreateResponseDto);
    }

}
