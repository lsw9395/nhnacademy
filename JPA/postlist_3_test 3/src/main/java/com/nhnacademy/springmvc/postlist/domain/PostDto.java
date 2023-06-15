package com.nhnacademy.springmvc.postlist.domain;

import com.nhnacademy.springmvc.postlist.entity.UserEntity;

import java.time.LocalDateTime;

public interface PostDto {
    String getTitle();
    String getContent();
    Long getId();
    UserEntity getUser();
    LocalDateTime getWriteTime();
    int getViewCount();
}
