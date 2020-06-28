package com.jpop4.controller;

import com.jpop4.dto.book.BookDto;
import com.jpop4.service.BookScreenService;
import com.jpop4.validation.DtoValidator;
import com.jpop4.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookScreenService bookService;

    @GetMapping("/{bookId}")
    public BookDto findBookDetails(@PathVariable BigInteger bookId) {
        return bookService.getBookDetails(bookId);
    }

    @GetMapping
    public List<BookDto> findAllBookDetails() {
        return bookService.getAllBookDetails();
    }

    @PostMapping
    public boolean addBookDetails(
            @DtoValidator(customValidator = BookValidator.class)
            @RequestBody BookDto bookDto) {
        return bookService.addBookDetails(bookDto);
    }

    @DeleteMapping("/{bookId}")
    public boolean removeBookDetails(@PathVariable BigInteger bookId) {
        return bookService.deleteBookDetails(bookId);
    }

    @PutMapping
    public BookDto updateBookDetails(
            @DtoValidator(customValidator = BookValidator.class)
            @RequestBody BookDto bookDto) {
        return bookService.updateBookDetails(bookDto);
    }
}
