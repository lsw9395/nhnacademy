package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.repository.MapUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class UserServiceTest {

    @Mock
    MapUserRepository userRepository;
    @InjectMocks
    UserService service;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void add() {
        User user = new User();
        user.setId("test");
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user);
        verify(userRepository,times(1)).getUserMap();
        assertEquals(user,service.getUser("test"));
    }

    @Test
    void modify() {
        User user = new User();
        user.setId("test");
        user.setName("test");
        User expect = new User();
        expect.setId(user.getId());
        expect.setName("test22");
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user);
        service.modify(expect);
        assertEquals(expect,service.getUser("test"));
    }

    @Test
    void remove() {
        User user = new User();
        user.setId("test");
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user);
        service.remove("test");
        assertNull(service.getUser("test"));
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId("test");
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user);
        assertEquals(user,service.getUser("test"));
    }

    @Test
    void getUsers() {
        User user1 = new User(); user1.setId("test1");
        User user2 = new User(); user2.setId("test2");
        User user3 = new User(); user3.setId("test3");
        List<User> expect = Arrays.asList(user2,user3,user1);
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user1);
        service.add(user2);
        service.add(user3);
        assertEquals(expect,service.getUsers());
    }

    @Test
    void getTotalCount() {
        User user1 = new User(); user1.setId("test1");
        User user2 = new User(); user2.setId("test2");
        User user3 = new User(); user3.setId("test3");
        List<User> expect = Arrays.asList(user1,user2,user3);
        when(userRepository.getUserMap()).thenReturn(new HashMap<>());
        service.add(user1);
        service.add(user2);
        service.add(user3);
        assertEquals(3,service.getTotalCount());
    }
}