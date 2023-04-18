package com.raymundo.doorshop.validation;

import com.raymundo.doorshop.dto.request.RegisterRequest;
import com.raymundo.doorshop.entity.UserEntity;
import com.raymundo.doorshop.repository.UserRepository;
import lombok.AllArgsConstructor;
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

    @Override
    public void validate(Object target, Errors errors) {
        if (!supports(target.getClass())) return;
        RegisterRequest registerRequest = (RegisterRequest) target;
        Optional<UserEntity> optional = userRepository.findByUsername(registerRequest.username());
        if (optional.isPresent())
            errors.rejectValue("username", "", "Username is already in use");
    }
}