package com.nhnacademy.springmvc.postlist.mapper;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
@Transactional
class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    void save() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        Assertions.assertNotNull(userMapper.getUsers());
    }

    @Test
    void update() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        User expect = new User(user.getId(),"1234","1234","1234");
        userMapper.update(expect);
        User actual = userMapper.getUser("user1");
        Assertions.assertEquals("1234",actual.getPassword());
        Assertions.assertEquals("1234",actual.getName());
    }

    @Test
    void remove() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        userMapper.remove(user.getId());
        assertNull(userMapper.getUser(user.getId()));
    }

    @Test
    void getUser() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        User actual = userMapper.getUser("user1");
        Assertions.assertEquals("1",actual.getPassword());
        Assertions.assertEquals("1",actual.getName());
    }

    @Test
    void getUsers() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        User user2 = new User("user2","1","1","1");
        userMapper.save(user2);
        Assertions.assertNotNull(userMapper.getUsers());
    }

    @Test
    void getTotalCount() {
        User user = new User("user1","1","1","1");
        userMapper.save(user);
        User user2 = new User("user2","1","1","1");
        userMapper.save(user2);
        Assertions.assertNotNull(userMapper.getTotalCount());
    }
}