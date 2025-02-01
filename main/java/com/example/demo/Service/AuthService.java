package com.example.demo.Service;

import com.example.demo.Model.dto.LoginReq;
import com.example.demo.Model.dto.LoginResponse;
import com.example.demo.Model.dto.UserRequestDto;

public interface AuthService {
    void register(UserRequestDto userReqDto);

    LoginResponse login(LoginReq loginReq);
}
