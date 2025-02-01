package com.example.demo.mapping;

import com.example.demo.Model.Author;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapping {
    Author toEntity(AuthorRequestDto authorRequestDto);
    AuthorResponseDto toResponse(Author author);
}
