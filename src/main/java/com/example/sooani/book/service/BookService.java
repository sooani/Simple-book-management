package com.example.sooani.book.service;

import com.example.sooani.book.dto.*;
import com.example.sooani.book.entity.Book;
import com.example.sooani.book.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Integer insert(BookCreateDto bookCreateDto) {
        Book book = Book.builder()
                .title(bookCreateDto.getTitle())
                .price(bookCreateDto.getPrice())
                .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }

    public BookReadResponseDto read(Integer bookId) throws NoSuchElementException {
        //bookId로부터 책정보를 읽어와서 정보를 사용해서 DTO객체를 생성하고 반환
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        //북레포지토리를 통해 주어진 bookId에 해당하는 책을 데이터베이스에서 찾아와
        //findById는 Optional을 반환 존재하지 않으면 orElseThrow메서드를 통해 NoSuchElementException을 발생시킴
        BookReadResponseDto bookReadResponseDto = new BookReadResponseDto();
        bookReadResponseDto.fromBook(book);
        return bookReadResponseDto;
    }

    //수정기능
    public void update(BookEditDto bookEditDto) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookEditDto.getBookId()).orElseThrow();
        book = bookEditDto.fill(book);
        this.bookRepository.save(book);
    }


    //수정화면을 보여주는 기능
    public BookEditResponseDto edit(Integer bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        return BookEditResponseDto.BookFactory(book);
    }

    public void delete(Integer bookId) throws NoSuchElementException {
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        this.bookRepository.delete(book);
    }

    public List<BookListResponseDto> bookList(String title, Integer page) {
        final int pageSize = 10;

        List<Book> books;

        if (page == null) {
            page = 0;
        } else {
            page -= 1;
        }

        if (title == null) {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "insertDateTime");
            books = this.bookRepository.findAll(pageable).toList();
        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Sort sort = Sort.by(Sort.Order.desc("insertDateTime"));
            pageable.getSort().and(sort);
            books = this.bookRepository.findByTitleContains(title, pageable);
        }

        return books.stream().map(book ->
                new BookListResponseDto(book.getBookId(), book.getTitle())
        ).collect(Collectors.toList());
    }
}
