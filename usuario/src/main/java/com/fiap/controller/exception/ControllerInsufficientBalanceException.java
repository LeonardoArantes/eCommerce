package com.fiap.controller.exception;

public class ControllerInsufficientBalanceException extends RuntimeException {
    public ControllerInsufficientBalanceException(String message) {
        super(message);
    }
}
