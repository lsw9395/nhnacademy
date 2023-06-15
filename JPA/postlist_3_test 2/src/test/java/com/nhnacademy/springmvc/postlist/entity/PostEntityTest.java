package com.nhnacademy.springmvc.postlist.entity;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

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
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("제목");
        postEntity.setContent("내용");
        postEntity.setWriteTime(LocalDateTime.now());
        postEntity.setViewCount(0);
        postEntity.setWriterUserId("user1");

        entityManager.persist(postEntity);
        //entityManager.persist(postEntity);
        entityManager.flush();

        PostEntity postEntity1 = entityManager.find(PostEntity.class,1l);
    }
}