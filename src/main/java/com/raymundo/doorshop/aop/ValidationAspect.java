package com.raymundo.doorshop.aop;

import com.raymundo.doorshop.dto.BaseDto;
import com.raymundo.doorshop.exception.ValidationException;
import com.raymundo.doorshop.validation.RegisterValidator;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Component
@Aspect
@AllArgsConstructor
public class ValidationAspect {

    private RegisterValidator registerValidator;

    @Pointcut(value = "execution(* com.raymundo.doorshop.controller..*(..))")
    public void controllerPointcut() {

    }

    @Before(value = "controllerPointcut()")
    public void validationAdvice(JoinPoint joinPoint) throws ValidationException {
        BaseDto baseDto = getDto(joinPoint);
        BindingResult bindingResult = getBindingResult(joinPoint);
        if (baseDto != null && bindingResult != null)
            validate(baseDto, bindingResult);
    }

    private BaseDto getDto(JoinPoint joinPoint) {
        for (Object o : joinPoint.getArgs())
            if (o instanceof BaseDto baseDto)
                return baseDto;
        return null;
    }

    private BindingResult getBindingResult(JoinPoint joinPoint) {
        for (Object o : joinPoint.getArgs())
            if (o instanceof BindingResult bindingResult)
                return bindingResult;
        return null;
    }

    private void validate(BaseDto baseDto, BindingResult bindingResult) throws ValidationException {
        registerValidator.validate(baseDto, bindingResult);
        if (bindingResult.hasErrors())
            throw new ValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }

}