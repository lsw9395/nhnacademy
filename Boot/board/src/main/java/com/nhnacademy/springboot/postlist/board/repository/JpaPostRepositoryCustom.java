package com.nhnacademy.springboot.postlist.board.repository;


import com.nhnacademy.springboot.postlist.board.entity.PostEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface JpaPostRepositoryCustom {
    List<PostEntity> getPostsByUserId(String id);
}
