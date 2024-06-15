package com.ecommerce.fiap.controller.exception;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
