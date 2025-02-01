package com.example.demo.Model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Validated
public class LoginReq {

    @NotBlank(message = "Username or email cannot be empty or null")
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z0-9_.@]+$")
    private String username;

    @NotBlank(message = "Password cannot be empty or null")
    @Size(min = 3)
    @Pattern(regexp = "[A-Za-z0-9_.]+")
    private String password;
}
