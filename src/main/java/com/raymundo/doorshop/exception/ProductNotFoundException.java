package com.raymundo.doorshop.exception;

public class ProductNotFoundException extends Exception {

    private static final String MESSAGE = "Product with id '%s' is not found";

    public ProductNotFoundException(String productId) {
        super(String.format(MESSAGE, productId));
    }
}
