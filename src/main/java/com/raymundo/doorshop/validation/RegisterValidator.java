package com.raymundo.doorshop.validation;

import com.raymundo.doorshop.dto.auth.request.RegisterRequest;
import com.raymundo.doorshop.entity.UserEntity;
import com.raymundo.doorshop.exception.UserAlreadyExistException;
import com.raymundo.doorshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RegisterValidator implements Validator {

    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegisterRequest.class);
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        if (!supports(target.getClass())) return;
        RegisterRequest registerRequest = (RegisterRequest) target;
        Optional<UserEntity> optional = userRepository.findByUsername(registerRequest.username());
        if (optional.isPresent())
            throw new UserAlreadyExistException(registerRequest.username());
    }
}