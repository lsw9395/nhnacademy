package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DbUserServiceTest {
    private static DbUserService service;
    private static UserMapper mapper;
    @BeforeAll
    static void  setUp(){
        mapper = mock(UserMapper.class);
        service = new DbUserService(mapper);
    }
    @Test
    void add() {
        User user = new User();
        user.setId("test");
        doNothing().when(mapper).save(user);
        service.add(user);
        verify(mapper,atLeastOnce()).save(any());
    }

    @Test
    void modify() {
        User user = new User();
        user.setId("test");
        user.setName("test");
        User expect = new User();
        expect.setId(user.getId());
        expect.setName("test22");
        doNothing().when(mapper).update(expect);
        service.add(user);
        service.modify(expect);
        verify(mapper,times(1)).update(any());
    }

    @Test
    void remove() {
        User user = new User();
        user.setId("test");
        user.setName("test");
        doNothing().when(mapper).remove(user.getId());
        service.add(user);
        service.remove(user.getId());
        verify(mapper,times(1)).remove(any());
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId("test");
        user.setName("test");
        when(mapper.getUser(any())).thenReturn(user);
        service.add(user);
        assertEquals(user,service.getUser(user.getId()));
    }

    @Test
    void getUsers() {
        User user = new User();
        user.setId("test1");
        user.setName("test");
        User user2 = new User();
        user2.setId("test2");
        user2.setName("test");
        User user3 = new User();
        user3.setId("test3");
        user3.setName("test");
        List<User> expect = Arrays.asList(user,user2,user3);
        service.add(user);service.add(user2);service.add(user3);
        when(mapper.getUsers()).thenReturn(expect);
        assertEquals(expect,mapper.getUsers());
    }

    @Test
    void getTotalCount() {
        User user = new User();
        user.setId("test1");
        user.setName("test");
        User user2 = new User();
        user2.setId("test2");
        user2.setName("test");
        User user3 = new User();
        user3.setId("test3");
        user3.setName("test");
        service.add(user);service.add(user2);service.add(user3);
        when(mapper.getTotalCount()).thenReturn(3l);
        assertEquals(3,mapper.getTotalCount());
    }
}