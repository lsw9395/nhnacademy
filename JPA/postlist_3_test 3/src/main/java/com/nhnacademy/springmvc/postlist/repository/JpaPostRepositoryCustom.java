package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface JpaPostRepositoryCustom {
    List<PostEntity> getPostsByUserId(String id);
}
