package com.raymundo.doorshop.util;

import com.raymundo.doorshop.validation.UUIDValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = FIELD)
@Retention(value = RUNTIME)
@Documented
@Constraint(validatedBy = UUIDValidator.class)
public @interface UUID {

    String message() default "Invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

