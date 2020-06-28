package com.jpop4.converter;

import com.jpop4.dto.book.BookDto;
import com.jpop4.repository.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    BookDto toBookDto(Book entity);
    Book toBook(BookDto bookDto);
    List<BookDto> toBookDtos(Iterable<Book> entities);
}
