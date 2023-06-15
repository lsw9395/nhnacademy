package com.nhnacademy.springboot.postlist.board.domain;



import com.nhnacademy.springboot.postlist.board.entity.UserEntity;

import java.time.LocalDateTime;

public interface PostDto {
    String getTitle();
    String getContent();
    Long getId();
    UserEntity getUser();
    LocalDateTime getWriteTime();
    Integer getViewCount();
}
