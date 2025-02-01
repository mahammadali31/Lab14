package com.example.demo.Controller;


import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Model.dto.BookRequestDto;
import com.example.demo.Model.dto.BookResponseDto;
import com.example.demo.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/add/{id}")
    public ResponseEntity<BookResponseDto> add(@PathVariable Long id, @RequestBody BookRequestDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.add(id,authorDto));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<BookResponseDto> update(@RequestBody BookRequestDto brandDto) {
        return new ResponseEntity<>(bookService.update(brandDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
