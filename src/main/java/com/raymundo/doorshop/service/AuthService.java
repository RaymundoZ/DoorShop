package com.raymundo.doorshop.service;

import com.raymundo.doorshop.dto.request.LoginRequest;
import com.raymundo.doorshop.dto.request.RegisterRequest;
import com.raymundo.doorshop.dto.response.UserResponse;
import com.raymundo.doorshop.entity.UserEntity;
import com.raymundo.doorshop.exception.LogoutException;
import com.raymundo.doorshop.repository.UserRepository;
import com.raymundo.doorshop.util.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    private SecurityContextHolderStrategy securityContextHolderStrategy;

    public UserResponse register(RegisterRequest registerRequest, BindingResult bindingResult) {
        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return user.toDto();
    }

    public UserResponse login(LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
        token.setDetails(webAuthenticationDetailsSource.buildDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = securityContextHolderStrategy.createEmptyContext();
        securityContext.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(securityContext);
        securityContextRepository.saveContext(securityContext, request, response);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return user.toDto();
    }

    public UserResponse logout(HttpServletRequest request, HttpServletResponse response) throws LogoutException {
        Authentication authentication = securityContextHolderStrategy.getContext().getAuthentication();
        if (authentication == null)
            throw new LogoutException("You are not logged in");

        UserEntity user = (UserEntity) authentication.getPrincipal();
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        return user.toDto();
    }

}