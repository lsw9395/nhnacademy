package com.nhnacademy.springmvc.postlist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class PostRegisterRequest {
    @Getter
    @Setter
    @NotBlank(message = "Title is empty")
    private String title;

    @Getter
    @Setter
    @NotBlank(message = "Content is empty")
    private String content;

}
