package com.example.demo.Service.ServiceImpl;

import com.example.demo.Model.User;
import com.example.demo.Model.dto.LoginReq;
import com.example.demo.Model.dto.LoginResponse;
import com.example.demo.Model.dto.UserRequestDto;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AuthService;
import com.example.demo.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(UserRequestDto userReqDto) {
        log.info("ActionLog.register.started: username {}", userReqDto.getUsername());


        if (userRepository.findByUsername(userReqDto.getUsername()).isPresent()) {
            log.warn("ActionLog.register.usernameAlreadyExists: username {}", userReqDto.getUsername());
            throw new RuntimeException("Username is already taken.");
        }

        User user = new User();
        user.setUsername(userReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
        user.setEmail(userReqDto.getEmail());
        user.setRole(Role.USER);


        userRepository.save(user);

        log.info("ActionLog.register.completed: username {}", userReqDto.getUsername());
    }

    @Override
    public LoginResponse login(LoginReq loginReq) {
        log.info("ActionLog.login.started: username {}", loginReq.getUsername());

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        String token = jwtService.createToken(new User(loginReq.getUsername()));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUsername(loginReq.getUsername());


        log.info("ActionLog.login.success: username {}", loginReq.getUsername());
        return loginResponse;
    }


}