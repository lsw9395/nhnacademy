package com.nhnacademy.springmvc.postlist.entity;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class UserEntityTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void test1(){
        UserEntity user = new UserEntity();
        user.setId("user1");
        user.setPassword("1234");
        user.setName("유저1");
        user.setProfileFileName("asd.jpg");

        entityManager.persist(user);
        entityManager.flush();

        UserEntity user2 = entityManager.find(UserEntity.class,"user1");
        assertEquals(user2,user);
    }
}