package com.raymundo.doorshop.exception;

public class UserAlreadyExistException extends Exception {

    private static final String MESSAGE = "Username '%s' is already exist";

    public UserAlreadyExistException(String username) {
        super(String.format(MESSAGE, username));
    }
}
