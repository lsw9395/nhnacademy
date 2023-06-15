package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.mapper.PostMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DbPostServiceTest {

    private static DbPostService service;
    private static PostMapper mapper;
    @BeforeAll
    static void setUp(){
        mapper = mock(PostMapper.class);
        service = new DbPostService(mapper);
    }
    @Order(1)
    @Test
    void register() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        doNothing().when(mapper).save(post);
        service.register(post);
        verify(mapper,atLeastOnce()).save(any());
    }

    @Test
    void modify() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        mapper.save(post);
        Post expect = new Post();
        expect.setId(post.getId());
        post.setTitle("test22");post.setContent("test22");
        doNothing().when(mapper).update(expect);
        service.update(expect);
        verify(mapper,times(1)).update(any());
    }

    @Test
    void remove() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        doNothing().when(mapper).save(post);
        service.register(post);
        doNothing().when(mapper).remove(post.getId());
        service.remove(post.getId());
        verify(mapper,times(1)).remove(post.getId());
    }

    @Test
    void getPost() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        when(mapper.getPost(anyLong())).thenReturn(post);
        service.register(post);
        assertEquals(post,service.getPost(1));
    }

    @Test
    void getPosts() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        Post post2 = new Post();
        post2.setTitle("test");post2.setContent("test");
        Post post3 = new Post();
        post3.setTitle("test");post3.setContent("test");
        List<Post> expect = Arrays.asList(post,post2,post3);
        service.register(post);service.register(post2);service.register(post3);
        when(mapper.getPosts()).thenReturn(expect);
        assertEquals(expect,mapper.getPosts());
    }

    @Test
    void getTotalCount() {
        Post post = new Post();
        post.setTitle("test");post.setContent("test");
        Post post2 = new Post();
        post2.setTitle("test");post2.setContent("test");
        Post post3 = new Post();
        post3.setTitle("test");post3.setContent("test");
        service.register(post);service.register(post2);service.register(post3);
        when(mapper.getTotalCount()).thenReturn(3l);
        assertEquals(3,mapper.getTotalCount());
    }
}