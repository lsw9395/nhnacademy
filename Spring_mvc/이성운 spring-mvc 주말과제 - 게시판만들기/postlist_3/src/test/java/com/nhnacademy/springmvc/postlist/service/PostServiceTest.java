package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.repository.MapPostRepository;
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
class PostServiceTest {
    @Mock
    MapPostRepository postRepository;
    @InjectMocks
    PostService service;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void register() {
        Post post = new Post();
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        Long postId = service.register(post);
        verify(postRepository,times(2)).getIndex();
        verify(postRepository,times(1)).getPostMap();
        assertEquals(Long.valueOf(1), postId);
    }

    @Test
    void modify() {
        Post post = new Post();
        post.setTitle("test1");
        post.setContent("test1");
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        Long postId = service.register(post);
        post.setId(postId);
        Post expect = new Post();
        expect.setTitle("test2");
        expect.setContent("test2");
        expect.setId(postId);
        service.modify(expect);
        Post actual = service.getPost(postId);
        assertEquals(expect.getId(),actual.getId());
    }

    @Test
    void remove() {
        Post post = new Post();
        post.setTitle("test1");
        post.setContent("test1");
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        Long postId = service.register(post);
        post.setId(postId);
        service.remove(postId);
        assertNull(service.getPost(postId));
    }

    @Test
    void getPost() {
        Post post = new Post();
        post.setTitle("test1");
        post.setContent("test1");
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        Long postId = service.register(post);
        post.setId(postId);
        assertEquals(service.getPost(postId),post);
    }

    @Test
    void getPosts() {
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        List<Post> expect = Arrays.asList(post1,post2,post3);
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        service.register(post1);
        service.register(post2);
        service.register(post3);
        assertEquals(expect,service.getPosts());
    }
    @Test
    void getTotalCount() {
        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        when(postRepository.getIndex()).thenReturn(new AtomicInteger(0));
        when(postRepository.getPostMap()).thenReturn(new HashMap<>());
        service.register(post1);
        service.register(post2);
        service.register(post3);
        assertEquals(3,service.getTotalCount());
    }
}