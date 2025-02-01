package com.example.demo.mapping;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapping {
    Book toEntity(BookRequestDto bookRequestDto);
    BookResponseDto toResponse(Book book);
}
