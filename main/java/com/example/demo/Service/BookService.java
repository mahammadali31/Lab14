package com.example.demo.Service;

import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;

public interface BookService {
    BookResponseDto add(Long id,BookRequestDto bookDto);

    BookResponseDto getById(Long id);

    BookResponseDto update(BookRequestDto bookDto);

    void delete(Long id);
}
