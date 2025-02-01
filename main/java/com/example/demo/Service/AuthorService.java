package com.example.demo.Service;

import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;

public interface AuthorService {
    AuthorResponseDto add(AuthorRequestDto bookDto);

    AuthorResponseDto getById(Long id);

    AuthorResponseDto update(AuthorRequestDto bookDto);

    void delete(Long id);
}
