package com.example.demo.Controller;


import com.example.demo.Model.Author;
import com.example.demo.Model.dto.AuthorRequestDto;
import com.example.demo.Model.dto.AuthorResponseDto;
import com.example.demo.Service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;


    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDto> add(@RequestBody AuthorRequestDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.add(authorDto));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<AuthorResponseDto> update(@RequestBody AuthorRequestDto brandDto) {
        return new ResponseEntity<>(authorService.add(brandDto), HttpStatus.OK);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
