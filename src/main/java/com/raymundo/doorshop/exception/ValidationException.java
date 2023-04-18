package com.raymundo.doorshop.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends Exception {

    private final List<String> messages;

    public ValidationException(List<String> messages) {
        this.messages = messages;
    }

}