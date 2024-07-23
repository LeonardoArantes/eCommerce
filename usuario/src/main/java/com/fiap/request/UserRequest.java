package com.fiap.request;


import com.fiap.model.enums.UserRole;

public record UserRequest(String login, String password, UserRole role) {
}