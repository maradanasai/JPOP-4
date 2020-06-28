package com.jpop4.service;

import com.jpop4.api.book.BookApi;
import com.jpop4.dto.book.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookScreenService {

    @Autowired
    private BookApi bookApi;

    public BookDto getBookDetails(BigInteger bookId) {
        return bookApi.findBookDetails(bookId);
    }

    public List<BookDto> getAllBookDetails() {
        return bookApi.findAllBookDetails();
    }

    public boolean addBookDetails(BookDto bookDto) {
        return bookApi.addBookDetails(bookDto);
    }

    public boolean deleteBookDetails(BigInteger bookId) {
        return bookApi.removeBookDetails(bookId);
    }

    public BookDto updateBookDetails(BookDto bookDto) {
        return bookApi.updateBookDetails(bookDto);
    }
}
