package com.nhnacademy.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.WebConfig;
import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.domain.DepartmentUpdateDto;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.service.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.DomainEvents;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = DepartmentController.class,excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = WebConfig.class)
})
@AutoConfigureMockMvc
class DepartmentControllerTest {
    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mvc;
    @Test
    @DisplayName("id를 통해서 department 가져오기")
    void getDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto("CS004","CS4팀");
        given(departmentService.getDepartment("CS004"))
                .willReturn(departmentDto);
        mvc.perform(get("/departments/{id}","CS004").header("X-USER-ID","nhnacademy"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.name",equalTo("CS4팀")));
    }
    @Test
    @DisplayName("찾는 id가 없는 경우")
    void getDepartment2() throws Exception {
        given(departmentService.getDepartment("CS00111"))
                .willThrow(new DepartmentNotFoundException("CS00111"));
        mvc.perform(get("/departments/{id}","CS00111").header("X-USER-ID","nhnacademy"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)));
    }
    @Test
    @DisplayName("부서 등록")
    void registerDepartment() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentDto departmentDto = new DepartmentDto("CS10101","CS10101팀");
        DepartmentResponse departmentResponse = new DepartmentResponse("CS10101");
        given(departmentService.registerDepartment(any()))
                .willReturn(departmentResponse);
        mvc.perform(post("/departments").header("X-USER-ID","nhnacademy").
                content(objectMapper.writeValueAsString(departmentDto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("이미 등록된 id의 부서")
    void registerDepartment2() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentDto departmentDto = new DepartmentDto("CS004","CS10101팀");
        departmentService.registerDepartment(departmentDto);
        given(departmentService.registerDepartment(any()))
                .willThrow(new DuplicateDepartmentIdException(departmentDto.getId()));
        mvc.perform(post("/departments").header("X-USER-ID","nhnacademy").
                        content(objectMapper.writeValueAsString(departmentDto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("부서 아이디 중복 : CS004")));

    }
    @Test
    @DisplayName("department 업데이트")
    void updateDepartment() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto("CS111팀");
        DepartmentResponse departmentResponse = new DepartmentResponse("CS005");
        mvc.perform(put("/departments/{id}","CS005").header("X-USER-ID","nhnacademy").
                        content(objectMapper.writeValueAsString(departmentUpdateDto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("존재하지 않는 부서의 업데이트")
    void updateDepartment2() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentUpdateDto departmentUpdateDto = new DepartmentUpdateDto("CS111팀");
        doThrow(new DepartmentNotFoundException(departmentUpdateDto.getName())).doNothing().when(departmentService).updateDepartment(any(),any());
        mvc.perform(put("/departments/{id}","CS00111").header("X-USER-ID","nhnacademy").
                        content(objectMapper.writeValueAsString(departmentUpdateDto)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("부서 삭제")
    void deleteDepartment() throws Exception {
        mvc.perform(delete("/departments/{id}","CS001").header("X-USER-ID","nhnacademy").
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("존재하지 않는 부서 삭제")
    void deleteDepartment2() throws Exception {
        doThrow(new DepartmentNotFoundException("CS00111")).doNothing().when(departmentService).deleteDepartment(any());
        mvc.perform(delete("/departments/{id}","CS00111").header("X-USER-ID","nhnacademy").
                        contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}