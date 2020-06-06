package com.java.taskManager.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
