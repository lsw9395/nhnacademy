package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

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
class PostViewControllerTest {
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
    @DisplayName("게시글 조회")
    void views() throws Exception {
        MockCookie cookie = new MockCookie("check_counter","");
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/post_view?id=1").cookie(cookie).session(session))
                .andDo(print())
                .andExpect(model().attributeExists("post"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/postview"));
    }
    @Test
    @DisplayName("게시글 조회 파라미터 에러")
    void viewsParamError() throws Exception {
        MockCookie cookie = new MockCookie("check_counter","");
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/post_view?i").cookie(cookie).session(session))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    void viewsIdError() throws Exception {
        MockCookie cookie = new MockCookie("check_counter","");
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/post_view?id=1001").cookie(cookie).session(session))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
}