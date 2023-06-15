package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.PostRegisterRequest;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.domain.UserRegisterRequest;
import com.nhnacademy.springmvc.postlist.service.PostService;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value={
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class UserRegisterControllerTest {
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
    @DisplayName("유저 등록페이지")
    void registerForm() throws Exception {
        UserRegisterRequest actual = (UserRegisterRequest) mockMvc.perform(get("/user_register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getRequest().getAttribute("userRegisterRequest");
        UserRegisterRequest expect = new UserRegisterRequest();
        assertEquals(expect.getId(),actual.getId());
    }

    @Test
    @DisplayName("유저 등록")
    void getUser() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setId("test12");
        userRegisterRequest.setPassword("12345");
        userRegisterRequest.setName("테스트");
        File file2 = new File("./src/test/webapp/resources/no_image.png");
        byte[] content = Files.readAllBytes(file2.toPath());
        MockMultipartFile file = new MockMultipartFile("file",
                                                "no_image.png",
                                                "image/png",
                                                content);
        log.info(file2.getName());
        log.info(file.getOriginalFilename());
        mockMvc.perform(multipart("/user_register").file(file)
                        .flashAttr("userRegisterRequest",userRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"));
    }
    @Test
    @DisplayName("유저 등록 Valid Test")
    void getUserValidTest() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setId("test");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setName("테스트");
        File file2 = new File("./src/test/webapp/resources/no_image.png");
        byte[] content = Files.readAllBytes(file2.toPath());
        MockMultipartFile file = new MockMultipartFile("file",
                "no_image.png",
                "image/png",
                content);
        log.info(file2.getName());
        log.info(file.getOriginalFilename());
        mockMvc.perform(multipart("/user_register").file(file)
                        .flashAttr("userRegisterRequest",userRegisterRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userregister"));
    }
    @Test
    @DisplayName("유저 등록 중복")
    void getUserDuplicated() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setId("user14");
        userRegisterRequest.setPassword("12323");
        userRegisterRequest.setName("테스트");
        File file2 = new File("./src/test/webapp/resources/no_image.png");
        byte[] content = Files.readAllBytes(file2.toPath());
        MockMultipartFile file = new MockMultipartFile("file",
                "no_image.png",
                "image/png",
                content);
        log.info(file2.getName());
        log.info(file.getOriginalFilename());
        mockMvc.perform(multipart("/user_register").file(file)
                        .flashAttr("userRegisterRequest",userRegisterRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
}