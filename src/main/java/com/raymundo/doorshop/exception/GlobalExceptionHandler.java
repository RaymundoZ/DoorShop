package com.raymundo.doorshop.exception;

import com.raymundo.doorshop.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    private ResponseEntity<ErrorResponse> validationExceptionHandler(ValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessages(), new Date());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    private ResponseEntity<ErrorResponse> badCredentialsExceptionHandler(BadCredentialsException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(e.getMessage()), new Date());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    private ResponseEntity<ErrorResponse> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(e.getMessage()), new Date());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(value = LogoutException.class)
    private ResponseEntity<ErrorResponse> logoutExceptionHandler(LogoutException e) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(e.getMessage()), new Date());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}