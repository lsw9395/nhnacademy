package com.nhnacademy.springmvc.postlist.mapper;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);
    void update(User user);
    void remove(String id);
    User getUser(String id);
    List<User> getUsers();
    long getTotalCount();
}
