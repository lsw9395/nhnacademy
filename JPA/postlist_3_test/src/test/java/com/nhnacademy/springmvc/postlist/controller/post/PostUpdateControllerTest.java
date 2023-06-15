package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.PostRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


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
class PostUpdateControllerTest {
    @Autowired
    WebApplicationContext context;

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
    @DisplayName("게시글 수정페이지 이동")
    void update() throws Exception {
        mockMvc.perform(get("/post_update?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/postregister"));
    }
    @Test
    @DisplayName("게시글 수정페이지 이동 파라미터 에러")
    void updateParamError() throws Exception {
        mockMvc.perform(get("/post_update?"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("존재하지 않는 게시글 수정페이지로 이동")
    void updateIdError() throws Exception {
        mockMvc.perform(get("/post_update?id=101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("게시글 수정")
    void getPost() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("제목입니다.");
        postRegisterRequest.setContent("내용입니다.");
        mockMvc.perform(post("/post_update?id=1").flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post_list?page=1"));
    }
    @Test
    @DisplayName("게시글 수정 valid error")
    void getPostValidError() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("");
        postRegisterRequest.setContent("");
        mockMvc.perform(post("/post_update?id=1").flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("post/postregister"));
    }
    @Test
    @DisplayName("존재하지 않는 게시글 수정")
    void getPostIdError() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("제목입니다.");
        postRegisterRequest.setContent("내용입니다.");
        mockMvc.perform(post("/post_update?id=101").flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("게시글 수정 파라미터 에러")
    void getPostParamError() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("제목입니다.");
        postRegisterRequest.setContent("내용입니다.");
        mockMvc.perform(post("/post_update?i").flashAttr("postRegisterRequest",postRegisterRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
}