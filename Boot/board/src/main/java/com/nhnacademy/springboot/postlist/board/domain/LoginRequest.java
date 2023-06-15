package com.nhnacademy.springboot.postlist.board.domain;

import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @Setter
    @NotBlank(message = "ID is empty")
    private String userId;

    @Setter
    @NotBlank(message = "PWD is empty")
    private String userPassword;
    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
}
