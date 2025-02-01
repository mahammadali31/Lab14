package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Model.dto.LoginReq;
import com.example.demo.Model.dto.LoginResponse;
import com.example.demo.Model.dto.UserRequestDto;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;



    @PostMapping("/sign-in")
    public LoginResponse login(@Valid @RequestBody LoginReq loginReq)  {
        return authService.login(loginReq);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDto userReqDto)  {
        authService.register(userReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
