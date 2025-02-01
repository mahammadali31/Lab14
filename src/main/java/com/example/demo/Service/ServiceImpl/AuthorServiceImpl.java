package com.example.demo.Service.ServiceImpl;

import com.example.demo.Model.Author;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Service.AuthorService;
import com.example.demo.mapping.AuthorMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapping authorMapping;

    @Override
    public AuthorResponseDto add(AuthorRequestDto authorDto) {
        Author author = authorMapping.toEntity(authorDto);
        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = authorMapping.toResponse(author);


        return authorResponseDto;
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        AuthorResponseDto authorResponseDto = authorMapping.toResponse(author);
        return authorResponseDto;
    }

    @Override
    public AuthorResponseDto update(AuthorRequestDto authorDto) {
        Author author = authorMapping.toEntity(authorDto);
        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = authorMapping.toResponse(author);


        return authorResponseDto;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
