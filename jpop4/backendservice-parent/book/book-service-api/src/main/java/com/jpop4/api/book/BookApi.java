package com.jpop4.api.book;


import com.jpop4.dto.book.BookDto;

import java.math.BigInteger;
import java.util.List;

public interface BookApi {

    List<BookDto> findAllBookDetails();

    BookDto findBookDetails(BigInteger bookId);

    boolean addBookDetails(BookDto bookDto);

    boolean removeBookDetails(BigInteger id);

    BookDto updateBookDetails(BookDto bookDto);
}
