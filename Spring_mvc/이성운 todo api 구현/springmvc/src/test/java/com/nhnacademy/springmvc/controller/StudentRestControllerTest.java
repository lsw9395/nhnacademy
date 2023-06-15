package com.nhnacademy.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.config.RootConfig;
import com.nhnacademy.springmvc.config.WebConfig;
import com.nhnacademy.springmvc.domain.Gender;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.DuplicateStudentIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class StudentRestControllerTest {
    @Autowired
    WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;
    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }
    @Test
    @DisplayName("학생등록")
    void registerStudent() throws Exception{
        StudentRegisterRequest srr = new StudentRegisterRequest();
        ReflectionTestUtils.setField(srr,"id","LSW9395");
        ReflectionTestUtils.setField(srr,"name","이성운스입니다람쥐렁이");
        ReflectionTestUtils.setField(srr,"age",26);
        ReflectionTestUtils.setField(srr,"gender", Gender.M);

        mockMvc.perform(post("/api/students")
                .content(objectMapper.writeValueAsString(srr))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    @DisplayName("학생등록-나이=50")
    void registerStudent_max_age_exception() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest,"id","leeee");
        ReflectionTestUtils.setField(studentRegisterRequest,"name","이성우느스르르르");
        ReflectionTestUtils.setField(studentRegisterRequest,"age",50);
        ReflectionTestUtils.setField(studentRegisterRequest,"gender", Gender.M);
        MvcResult mvcResult =  mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRegisterRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();

        String message = mvcResult.getResolvedException().getMessage();
    }
    @Test
    @DisplayName("학생등록_아이디중복")
    void registerStudent_exist_id() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest,"id","student1");
        ReflectionTestUtils.setField(studentRegisterRequest,"name","이성운");
        ReflectionTestUtils.setField(studentRegisterRequest,"age",26);
        ReflectionTestUtils.setField(studentRegisterRequest,"gender", Gender.M);

        MvcResult mvcResult =  mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRegisterRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(DuplicateStudentIdException.class,mvcResult.getResolvedException().getClass());
    }
    @Test
    @DisplayName("학생조회")
    void getStudent() throws Exception {
        mockMvc.perform(get("/api/students/LSW9395"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("LSW9395"))
                .andExpect(jsonPath("$.name").value("이성운스입니다람쥐렁이"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.age").value("26"))
                .andReturn();
    }
    @Test
    @DisplayName("학생수정")
    void modifyStudent() throws Exception {
        StudentRegisterRequest srr = new StudentRegisterRequest();
        //ReflectionTestUtils.setField(srr,"id","LSW9395");
        ReflectionTestUtils.setField(srr,"name","이성운");
        ReflectionTestUtils.setField(srr,"age",21);
        ReflectionTestUtils.setField(srr,"gender", Gender.M);
        mockMvc.perform(put("/api/students/student1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(srr)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}