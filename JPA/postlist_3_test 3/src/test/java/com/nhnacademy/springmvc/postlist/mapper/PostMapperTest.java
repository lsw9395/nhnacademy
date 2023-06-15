package com.nhnacademy.springmvc.postlist.mapper;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.domain.Post;
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
class PostMapperTest {
    @Autowired
    PostMapper postMapper;
    @Test
    void save() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        Assertions.assertNotEquals(post.getId(),0);
    }

    @Test
    void update() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        Post change = new Post(post.getId(),"test2","test2",post.getWriterUserId(),post.getWriteTime(),post.getViewCount());
        postMapper.update(change);
        Post actual = postMapper.getPost(post.getId());
        Assertions.assertEquals("test2",actual.getTitle());
        Assertions.assertEquals("test2",actual.getContent());
    }

    @Test
    void remove() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        postMapper.remove(post.getId());
        assertNull(postMapper.getPost(post.getId()));
    }

    @Test
    void getPost() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        Post actual = postMapper.getPost(post.getId());
        Assertions.assertEquals("test",actual.getTitle());
        Assertions.assertEquals("test",actual.getContent());
    }

    @Test
    void getPosts() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        Post post2 = new Post();
        post2.setTitle("test");
        post2.setContent("test");
        post2.setWriterUserId("user1");
        postMapper.save(post2);
        Assertions.assertNotNull(postMapper.getPosts());
    }

    @Test
    void getTotalCount() {
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
        post.setWriterUserId("user1");
        postMapper.save(post);
        Post post2 = new Post();
        post2.setTitle("test");
        post2.setContent("test");
        post2.setWriterUserId("user1");
        postMapper.save(post2);
        Assertions.assertNotNull(postMapper.getTotalCount());
    }
}