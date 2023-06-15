package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value={
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class UserListControllerTest {
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
    @DisplayName("유저 리스트 조회")
    void viewTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/user_list?page=1").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userlist"));
    }
    @Test
    @DisplayName("유저 조회 페이지 수가 초과")
    void viewParamTest() throws Exception {
        mockMvc.perform(get("/user_list?page=101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("페이지에 문자 들어옴")
    void viewParamFormatError() throws Exception {
        mockMvc.perform(get("/user_list?page=asdf101"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("유저 조회 페이지 수가 초과")
    void viewParamError() throws Exception {
        mockMvc.perform(get("/user_list?"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
}