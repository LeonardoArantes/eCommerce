package com.fiap.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    private StandardError error = new StandardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Entity not found");
        error.setMenssage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(status.value());
        validateError.setError("Erro de validação.");
        validateError.setMenssage(e.getMessage());
        validateError.setPath(request.getRequestURI());

        for (FieldError f: e.getBindingResult().getFieldErrors()){
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validateError);
    }

    @ExceptionHandler(ControllerInsufficientBalanceException.class)
    public ResponseEntity<StandardError> entityNotFound(ControllerInsufficientBalanceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_MODIFIED;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Saldo insuficiente");
        error.setMenssage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }

}
