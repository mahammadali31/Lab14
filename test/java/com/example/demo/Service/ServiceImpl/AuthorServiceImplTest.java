package com.example.demo.Service.ServiceImpl;

import com.example.demo.Model.Author;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Service.AuthorService;
import com.example.demo.mapping.AuthorMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapping authorMapping;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorRequestDto authorRequestDto;
    private AuthorResponseDto authorResponseDto;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "John Doe", "American");
        authorRequestDto = new AuthorRequestDto("John Doe", "American");
        authorResponseDto = new AuthorResponseDto(1L, "John Doe", "American");
    }

    @Test
    void testAddAuthor() {
        when(authorMapping.toEntity(authorRequestDto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapping.toResponse(author)).thenReturn(authorResponseDto);

        AuthorResponseDto response = authorService.add(authorRequestDto);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testGetById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorMapping.toResponse(author)).thenReturn(authorResponseDto);

        AuthorResponseDto response = authorService.getById(1L);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAuthor() {
        when(authorMapping.toEntity(authorRequestDto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapping.toResponse(author)).thenReturn(authorResponseDto);

        AuthorResponseDto response = authorService.update(authorRequestDto);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.delete(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
