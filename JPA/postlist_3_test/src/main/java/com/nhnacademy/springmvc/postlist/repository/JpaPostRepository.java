package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findByUser_Name(String userName);
    List<PostEntity> findByUser_Id(String userId);
    List<PostEntity> findByTitleLike(String title);
    List<PostEntity> findByContentLike(String content);
    List<PostEntity> findByTitleLikeAndContentLike(String title, String content);

    UserEntity findUserById(Long id);
}
