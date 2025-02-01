package com.example.demo.mapping;

import com.example.demo.Model.Author;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-02T00:57:49+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AuthorMappingImpl implements AuthorMapping {

    @Override
    public Author toEntity(AuthorRequestDto authorRequestDto) {
        if ( authorRequestDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setName( authorRequestDto.getName() );
        author.setNationality( authorRequestDto.getNationality() );

        return author;
    }

    @Override
    public AuthorResponseDto toResponse(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        authorResponseDto.setId( author.getId() );
        authorResponseDto.setName( author.getName() );
        authorResponseDto.setNationality( author.getNationality() );

        return authorResponseDto;
    }
}
