package com.example.demo.mapping;

import com.example.demo.Model.Book;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-02T00:57:49+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BookMappingImpl implements BookMapping {

    @Override
    public Book toEntity(BookRequestDto bookRequestDto) {
        if ( bookRequestDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( bookRequestDto.getTitle() );
        book.setPrice( bookRequestDto.getPrice() );

        return book;
    }

    @Override
    public BookResponseDto toResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setId( book.getId() );
        bookResponseDto.setTitle( book.getTitle() );
        bookResponseDto.setPrice( book.getPrice() );

        return bookResponseDto;
    }
}
