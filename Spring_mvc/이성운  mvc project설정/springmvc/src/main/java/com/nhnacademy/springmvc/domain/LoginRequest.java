package com.nhnacademy.springmvc.domain;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "ID is empty")
    private String userId;
    @NotBlank(message = "PWD is empty")
    private String userPassword;
    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
}