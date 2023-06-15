package com.nhnacademy.springmvc.postlist.controller.post;


import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.domain.PostRegisterRequest;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.service.DbPostService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value={
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class PostRegisterControllerTest {
    @Autowired
    WebApplicationContext context;

    @Mock
    DbPostService service;

    MockMvc mockMvc;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }
    @Test
    @DisplayName("게시글 등록페이지")
    void registerForm() throws Exception {
        PostRegisterRequest actual = (PostRegisterRequest) mockMvc.perform(get("/post_register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getRequest().getAttribute("postRegisterRequest");
        PostRegisterRequest expect = new PostRegisterRequest();
        assertEquals(expect.getTitle(),actual.getTitle());
    }

    @Test
    @DisplayName("게시글 등록")
    void getPost() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("제목입니다.");
        postRegisterRequest.setContent("내용입니다.");
        Post post = new Post(2l,"제목입니다.","내용입니다.","user1",null,0);
        User user = new User("user1","12345","유저1",null);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",user);
        mockMvc.perform(post("/post_register").session(session)
                .flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post_list?page=1"));
        when(service.getPost(2)).thenReturn(post);
        Post expect = service.getPost(2);
        assertEquals(expect.getId(),post.getId());
    }
    @Test
    @DisplayName("관리자 게시글 등록")
    void getPostAdmin() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("제목입니다.");
        postRegisterRequest.setContent("내용입니다.");
        Post post = new Post(2l,"제목입니다.","내용입니다.","user1",null,0);
        User admin = new User("admin","12345","관리자",null);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("admin",admin);
        mockMvc.perform(post("/post_register").session(session)
                        .flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post_list?page=1"));
        when(service.getPost(2)).thenReturn(post);
        Post expect = service.getPost(2);
        assertEquals(expect.getId(),post.getId());
    }
    @Test
    @DisplayName("validError")
    void postValidError() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("");
        postRegisterRequest.setContent("");
        User user = new User("user1","12345","유저1",null);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user",user);
        mockMvc.perform(post("/post_register").session(session)
                        .flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/postregister"));
    }
}