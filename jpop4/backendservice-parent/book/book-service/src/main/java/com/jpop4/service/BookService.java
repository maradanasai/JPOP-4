package com.jpop4.service;

import com.jpop4.api.book.BookApi;
import com.jpop4.converter.BookMapper;
import com.jpop4.dto.book.BookDto;
import com.jpop4.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookService implements BookApi {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDto> findAllBookDetails() {
        return bookMapper
                .toBookDtos(bookRepository.findAll());
    }

    @Override
    public BookDto findBookDetails(BigInteger bookId) {
        return bookMapper
                .toBookDto(bookRepository
                        .findById(bookId)
                        .orElse(null));
    }

    @Override
    public boolean addBookDetails(BookDto bookDto) {
        bookRepository.save(bookMapper.toBook(bookDto));
        LOG.debug("Book details {} saved successfully", bookDto);
        return true;
    }

    @Override
    public boolean removeBookDetails(BigInteger id) {
        bookRepository.deleteById(id);
        LOG.debug("Book details with id {} deleted successfully", id);
        return true;
    }

    @Override
    public BookDto updateBookDetails(BookDto bookDto) {
        bookRepository.save(bookMapper.toBook(bookDto));
        LOG.debug("Book details {} updated successfully", bookDto);
        return bookDto;
    }
}
