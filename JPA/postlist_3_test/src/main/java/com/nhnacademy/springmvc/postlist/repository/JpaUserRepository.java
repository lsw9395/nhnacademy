package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByName(String name);
}
