package com.nhnacademy.springmvc.postlist.entity;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.repository.JpaPostRepository;
import com.nhnacademy.springmvc.postlist.repository.JpaUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class PostEntityTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void test1(){
        UserEntity user = new UserEntity();
        user.setId("user4");
        user.setPassword("1234");
        user.setName("유저1");
        user.setProfileFileName("user1.jpg");

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("제목");
        postEntity.setContent("내용");
        postEntity.setWriteTime(LocalDateTime.now());
        postEntity.setViewCount(0);
        postEntity.setUser(user);

        PostEntity postEntity2 = new PostEntity();
        postEntity2.setTitle("제목2");
        postEntity2.setContent("내용2");
        postEntity2.setWriteTime(LocalDateTime.now());
        postEntity2.setViewCount(0);
        postEntity2.setUser(user);

        user.setPostEntities(Arrays.asList(postEntity,postEntity2));
        entityManager.persist(user);
        entityManager.flush();
    }
}