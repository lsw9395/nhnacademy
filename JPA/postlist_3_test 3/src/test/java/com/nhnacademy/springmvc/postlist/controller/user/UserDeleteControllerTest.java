package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
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

import javax.servlet.http.HttpSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value={
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class UserDeleteControllerTest {
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
    @DisplayName("유저 삭제")
    void delete() throws Exception {
        mockMvc.perform(get("/user_delete?id=30"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user_list?page=1"));
    }
    @Test
    @DisplayName("존재하지 않는 유저 삭제")
    void deleteError() throws Exception {
        mockMvc.perform(get("/user_delete?id=user101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("/error"));
    }
    @Test
    @DisplayName("잘못된 파라미터 입력")
    void deleteParamError() throws Exception {
        mockMvc.perform(get("/user_delete?i"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("/error"));
    }
}