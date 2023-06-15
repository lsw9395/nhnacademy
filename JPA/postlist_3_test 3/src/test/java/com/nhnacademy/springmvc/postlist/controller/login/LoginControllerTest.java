package com.nhnacademy.springmvc.postlist.controller.login;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.LoginRequest;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


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
class LoginControllerTest {
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
    @DisplayName("로그인 페이지 이동")
    void login() throws Exception {
        LoginRequest loginRequest = (LoginRequest) mockMvc.perform(get("/login/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"))
                .andDo(print())
                .andReturn()
                .getRequest()
                .getAttribute("loginRequest");
        assertNull(loginRequest.getUserId());
    }

    @Test
    @Order(1)
    @DisplayName("admin login")
    void adminLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("admin");
        loginRequest.setUserPassword("12345");
        User expect = new User("admin","12345","admin",null);

        HttpSession session = mockMvc.perform(post("/login")
                        .flashAttr("loginRequest", loginRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login/login"))
                .andReturn()
                .getRequest().getSession();
        User actal = (User) session.getAttribute("admin");
        assertEquals(expect.getId(),actal.getId());
    }
    @Test
    @Order(2)
    @DisplayName("user login")
    void userLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("user4");
        loginRequest.setUserPassword("4");
        User expect = new User("user4","1","유저1",null);

        HttpSession session = mockMvc.perform(post("/login")
                        .flashAttr("loginRequest", loginRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login/login"))
                .andReturn()
                .getRequest().getSession();
        User actal = (User) session.getAttribute("user");
        assertEquals(expect.getId(),actal.getId());
    }
    @Test
    @DisplayName("valid error")
    void validTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("");
        loginRequest.setUserPassword("");

        mockMvc.perform(post("/login")
                        .flashAttr("loginRequest", loginRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/login/loginForm"));
    }
    @Test
    @DisplayName("login fail")
    void loginFailTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("user1");
        loginRequest.setUserPassword("2");


        mockMvc.perform(post("/login")
                        .flashAttr("loginRequest", loginRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(flash().attributeExists("message"))
                .andExpect(view().name("redirect:/login/"));
    }
}