package com.example.demo.Service.ServiceImpl;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;
import com.example.demo.mapping.AuthorMapping;
import com.example.demo.mapping.BookMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapping authorMapping;
    private final AuthorRepository authorRepository;
    private final BookMapping bookMapping;


    @Transactional
    @Override
    public BookResponseDto add(Long id, BookRequestDto bookDto) {
        Author author = authorRepository.findById(id).orElseThrow();
        Book book = authorMapping.toEntity(bookDto);
        book.setAuthor(author);
        book = bookRepository.save(book);

        BookResponseDto responseDto = authorMapping.toResponse(book);


        return responseDto;
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        BookResponseDto authorResponseDto = bookMapping.toResponse(book);
        return authorResponseDto;
    }

    @Override
    public BookResponseDto update(BookRequestDto bookDto) {
        Book book = authorMapping.toEntity(bookDto);
        bookRepository.save(book);

        BookResponseDto bookResponseDto = authorMapping.toResponse(book);


        return bookResponseDto;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
