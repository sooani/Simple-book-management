package com.example.sooani.book.service;

import com.example.sooani.book.dto.BookLogCreateDto;
import com.example.sooani.book.dto.BookLogCreateResponseDto;
import com.example.sooani.book.entity.Book;
import com.example.sooani.book.entity.BookLog;
import com.example.sooani.book.repository.BookLogRepository;
import com.example.sooani.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookLogService {
    private BookRepository bookRepository;
    private BookLogRepository bookLogRepository;

    public BookLogService(BookRepository bookRepository, BookLogRepository bookLogRepository) {
        this.bookRepository = bookRepository;
        this.bookLogRepository = bookLogRepository;
    }

    public BookLogCreateResponseDto insert(BookLogCreateDto bookLogCreateDto) {
        Book book = this.bookRepository.findById(bookLogCreateDto.getBookId()).orElseThrow();

        BookLog bookLog = BookLog.builder()
                .book(book)
                .comment(bookLogCreateDto.getComment())
                .page(bookLogCreateDto.getPage())
                .build();
        bookLog = this.bookLogRepository.save(bookLog);

        return BookLogCreateResponseDto.BookLogFactory(bookLog);
    }
}
