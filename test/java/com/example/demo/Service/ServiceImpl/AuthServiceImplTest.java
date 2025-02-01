package com.example.demo.Service.ServiceImpl;
import com.example.demo.Model.User;
import com.example.demo.Model.dto.LoginReq;
import com.example.demo.Model.dto.LoginResponse;
import com.example.demo.Model.dto.UserRequestDto;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ServiceImpl.AuthServiceImpl;
import com.example.demo.Service.ServiceImpl.JwtService;
import com.example.demo.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Test
    void register_success() {
        UserRequestDto userReqDto = new UserRequestDto();
        userReqDto.setUsername("testuser");
        userReqDto.setPassword("password");
        userReqDto.setEmail("test@example.com");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        authService.register(userReqDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals(Role.USER, savedUser.getRole());
    }

    @Test
    void register_usernameAlreadyExists() {
        UserRequestDto userReqDto = new UserRequestDto();
        userReqDto.setUsername("existinguser");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> authService.register(userReqDto));

        verify(userRepository, never()).save(any());
    }

    @Test
    void login_success() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("testuser");
        loginReq.setPassword("password");

        when(authenticationManager.authenticate(any())).thenReturn(null); // Simulate successful authentication
        when(jwtService.createToken(any())).thenReturn("mockedToken");

        LoginResponse loginResponse = authService.login(loginReq);

        assertEquals("mockedToken", loginResponse.getToken());
        assertEquals("testuser", loginResponse.getUsername());
    }



    @Test
    void login_authenticationFails() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("testuser");
        loginReq.setPassword("wrongPassword");

        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> authService.login(loginReq));
        verify(jwtService, never()).createToken(any()); // Token should not be generated
    }

}
