package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.UserRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value={
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class UserUpdateControllerTest {
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
    @DisplayName("유저 수정페이지 이동")
    void views() throws Exception {
        mockMvc.perform(get("/user_update?id=20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userregister"));
    }
    @Test
    @DisplayName("유저 수정페이지 이동 파라미터 에러")
    void updateParamError() throws Exception {
        mockMvc.perform(get("/user_update?"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("존재하지 않는 유저 수정페이지로 이동")
    void updateIdError() throws Exception {
        mockMvc.perform(get("/user_update?id=user200"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("유저 수정")
    void getUser() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setId("user123123");
        userRegisterRequest.setPassword("12345");
        userRegisterRequest.setName("테스트");
        File file2 = new File("./src/test/webapp/resources/no_image.png");
        byte[] content = Files.readAllBytes(file2.toPath());
        MockMultipartFile file = new MockMultipartFile("file",
                "no_image.png",
                "image/png",
                content);
    }
    @Test
    @DisplayName("유저 수정 Valid Test")
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
        mockMvc.perform(multipart("/user_update").file(file)
                        .flashAttr("userRegisterRequest",userRegisterRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userregister"));
    }
}