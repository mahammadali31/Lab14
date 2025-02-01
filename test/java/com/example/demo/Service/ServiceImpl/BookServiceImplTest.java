package com.example.demo.Service.ServiceImpl;
import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.ServiceImpl.BookServiceImpl;
import com.example.demo.mapping.BookMapping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapping bookMapping;

    @Test
    void add_success() {
        Long authorId = 1L;
        BookRequestDto bookDto = new BookRequestDto();
        bookDto.setTitle("Test Book");
        bookDto.setPrice(BigDecimal.TEN);

        Author author = new Author();
        author.setId(authorId);

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(author);

        BookResponseDto expectedResponse = new BookResponseDto();
        expectedResponse.setTitle(book.getTitle());
        expectedResponse.setPrice(book.getPrice());

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(bookMapping.toEntity(bookDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapping.toResponse(book)).thenReturn(expectedResponse);

        BookResponseDto actualResponse = bookService.add(authorId, bookDto);

        assertEquals(expectedResponse, actualResponse);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookCaptor.capture());
        Book capturedBook = bookCaptor.getValue();

        assertEquals(bookDto.getTitle(), capturedBook.getTitle());
        assertEquals(bookDto.getPrice(), capturedBook.getPrice());
        assertEquals(author, capturedBook.getAuthor());
    }

    @Test
    void add_authorNotFound() {
        Long authorId = 1L;
        BookRequestDto bookDto = new BookRequestDto();

        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> bookService.add(authorId, bookDto));

        verify(bookRepository, never()).save(any());
    }

    @Test
    void getById_success() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setPrice(BigDecimal.TEN);

        BookResponseDto expectedResponse = new BookResponseDto();
        expectedResponse.setId(book.getId());
        expectedResponse.setTitle(book.getTitle());
        expectedResponse.setPrice(book.getPrice());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapping.toResponse(book)).thenReturn(expectedResponse);

        BookResponseDto actualResponse = bookService.getById(bookId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getById_bookNotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> bookService.getById(bookId));
    }

    @Test
    void update_success() {
        // Setup
        BookRequestDto bookDto = new BookRequestDto();
        bookDto.setTitle("Updated Book Title");
        bookDto.setPrice(BigDecimal.ONE);

        Book existingBook = new Book();
        existingBook.setTitle("Original Book Title"); // The title must exist in the database
        existingBook.setPrice(BigDecimal.TEN);

        Book updatedBook = new Book();
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setPrice(bookDto.getPrice());

        BookResponseDto expectedResponse = new BookResponseDto();
        expectedResponse.setTitle(updatedBook.getTitle());
        expectedResponse.setPrice(updatedBook.getPrice());

        when(bookMapping.toEntity(bookDto)).thenReturn(updatedBook);
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
        when(bookMapping.toResponse(updatedBook)).thenReturn(expectedResponse);

        // Act
        BookResponseDto actualResponse = bookService.update(bookDto);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookCaptor.capture());
        Book capturedBook = bookCaptor.getValue();
        assertEquals(bookDto.getTitle(), capturedBook.getTitle());
        assertEquals(bookDto.getPrice(), capturedBook.getPrice());
    }


    @Test
    void delete_success() {
        Long bookId = 1L;

        bookService.delete(bookId);

        verify(bookRepository).deleteById(bookId);
    }
}