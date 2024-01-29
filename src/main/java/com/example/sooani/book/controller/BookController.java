package com.example.sooani.book.controller;

import com.example.sooani.book.dto.*;
import com.example.sooani.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller //브라우저의 요청(request을 받아들이는 컨트롤러임을 인지해서 빈으로 등록)
public class BookController {

    @Autowired //해당 타입의 빈 의존성 자동주입
    private BookService bookService;

    @GetMapping("/book/create")
    public String create() {
        return "book/create";
    }

    @PostMapping("/book/create")
    public String insert(BookCreateDto bookCreateDto) {
        Integer bookId = this.bookService.insert(bookCreateDto);
        return String.format("redirect:/book/read/%s", bookId);
    }

    @GetMapping("/book/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId) {
        ModelAndView mav = new ModelAndView();

        try {
            BookReadResponseDto bookReadResponseDto = this.bookService.read(bookId);
            mav.addObject("bookReadResponseDto", bookReadResponseDto);
            mav.setViewName("book/read");

        } catch (NoSuchElementException ex) {
            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            mav.addObject("message", "책 정보가 없습니다.");
            mav.addObject("location", "/book");
            mav.setViewName("common/error/422");
        }

        return mav;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
        return this.error422("책 정보가 없습니다.", "/book/list");
    }

    private ModelAndView error422(String message, String location) {
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("message", message);
        mav.addObject("location", location);
        mav.setViewName("common/error/422");
        return mav;
    }

    @GetMapping("/book/edit/{bookId}")
    public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {
        ModelAndView mav = new ModelAndView();
        BookEditResponseDto bookEditResponseDto = this.bookService.edit(bookId);
        mav.addObject("bookEditResponseDto", bookEditResponseDto);
        mav.setViewName("book/edit");
        return mav;
    }

    @PostMapping("/book/edit/{bookId}")
    public ModelAndView update(
            @Validated BookEditDto bookEditDto,
            Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage =
                    errors
                            .getFieldErrors()
                            .stream()
                            .map(x -> x.getField() + " : " + x.getDefaultMessage())
                            .collect(Collectors.joining("\n"));

            return this.error422(
                    errorMessage,
                    String.format("/book/edit/%s", bookEditDto.getBookId())
            );
        }

        this.bookService.update(bookEditDto);

        ModelAndView mav = new ModelAndView();
        mav.setViewName(String.format("redirect:/book/read/%s", bookEditDto.getBookId()));
        return mav;
    }

    @PostMapping("/book/delete")
    public String delete(Integer bookId) throws NoSuchElementException {
        this.bookService.delete(bookId);
        return "redirect:/book/list";
    }

    @GetMapping(value = {"/book/list", "/book"})
    public ModelAndView bookList(String title, Integer page, ModelAndView mav) {
        mav.setViewName("/book/list");

        List<BookListResponseDto> books = this.bookService.bookList(title, page);
        mav.addObject("books", books);
        return mav;
    }
}
