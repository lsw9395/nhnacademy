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
class UserViewControllerTest {
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
    @DisplayName("유저 조회")
    void views() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/user_view?id=user10").session(session))
                .andDo(print())
                .andExpect(model().attributeExists("user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userview"));
    }
    @Test
    @DisplayName("유저 조회 관리자")
    void viewsAdmin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("admin",user);
        mockMvc.perform(get("/user_view?id=user20").session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userview"));
    }
    @Test
    @DisplayName("유저 조회 파라미터 에러")
    void viewsParamError() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/user_view?i").session(session))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("존재하지 않는 유저 조회")
    void viewsIdError() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = new User();
        session.setAttribute("user",user);
        mockMvc.perform(get("/user_view?id=1012").session(session))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
}