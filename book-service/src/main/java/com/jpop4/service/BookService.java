package com.jpop4.service;

import com.jpop4.domain.BookDto;
import com.jpop4.mapper.BookMapper;
import com.jpop4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookScreenService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public BookDto getBookDetails(BigInteger bookId) {
        return bookMapper
                .toBookDto(
                        bookRepository.findById(bookId)
                                .orElse(null)
                );
    }

    public List<BookDto> getAllBookDetails() {
        return bookMapper.toBookDtos(bookRepository.findAll());
    }

    public boolean addBookDetails(BookDto bookDto) {
        bookRepository.save(bookMapper.toBook(bookDto));
        return true;
    }

    public boolean deleteBookDetails(BigInteger bookId) {
        bookRepository.deleteById(bookId);
        return true;
    }

    public BookDto updateBookDetails(BookDto bookDto) {
        bookRepository.save(bookMapper.toBook(bookDto));
        return bookDto;
    }
}