package com.raymundo.doorshop.controller;

import com.raymundo.doorshop.dto.auth.request.LoginRequest;
import com.raymundo.doorshop.dto.auth.request.RegisterRequest;
import com.raymundo.doorshop.dto.auth.response.UserResponse;
import com.raymundo.doorshop.exception.LogoutException;
import com.raymundo.doorshop.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request,
                                              HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(loginRequest, request, response));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<UserResponse> logout(HttpServletRequest request, HttpServletResponse response) throws LogoutException {
        return ResponseEntity.ok(authService.logout(request, response));
    }

}