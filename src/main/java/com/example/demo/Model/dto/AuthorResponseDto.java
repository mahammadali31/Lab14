package com.example.demo.Model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;

    private String name;
    private String nationality;
}
