package com.jpop4.converter;

import com.jpop4.domain.common.Book;
import com.jpop4.dto.book.BookDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    BookDto toBookDto(Book bookEntity);
    Book toBook(BookDto bookDto);
    List<BookDto> toBookDtos(Iterable<Book> bookEntities);
}
