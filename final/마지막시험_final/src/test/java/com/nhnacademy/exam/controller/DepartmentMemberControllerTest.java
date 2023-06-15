package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.WebConfig;
import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.Employee;
import com.nhnacademy.exam.service.DepartmentMemberService;
import com.nhnacademy.exam.service.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentMemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private DepartmentMemberService departmentMemberService;
    @Test
    @DisplayName("부서에 포함된 멤버 출력")
    void getDepartmentMembers() throws Exception {
        List<DepartmentMemberDto> list = List.of(new DepartmentMemberDto(new Department("CS005","CS005팀"),new Employee("1","직원")),
                new DepartmentMemberDto(new Department("CS005","CS005팀"),new Employee("2","직원")));
        given(departmentMemberService.getDepartmentMemberWithIds(anyString()))
                .willReturn(list);
        mvc.perform(get("/department-members?departmentIds=CS005").header("X-USER-ID","nhnacademy").header("Accept","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$[0].department.id",equalTo("CS005")));
    }
    @Test
    @DisplayName("파라미터 미싱")
    void getDepartmentMembers2() throws Exception {
        mvc.perform(get("/department-members?departmentIds=").header("X-USER-ID","nhnacademy").header("Accept","application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("Required request parameter 'departmentIds' for method parameter type String is not present")));
    }
    @Test
    @DisplayName("파라미터 누락")
    void getDepartmentMembers3() throws Exception {
        mvc.perform(get("/department-members?").header("X-USER-ID","nhnacademy").header("Accept","application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("Required request parameter 'departmentIds' for method parameter type String is not present")));
    }
    @Test
    @DisplayName("X-USER-ID가 marco")
    void getDepartmentMembers4() throws Exception {
        mvc.perform(get("/department-members?departmentIds=CS001").header("X-USER-ID","marco").header("Accept","application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("Unauthorized")));
    }
    @Test
    @DisplayName("X-USER-ID가 누락")
    void getDepartmentMembers5() throws Exception {
        mvc.perform(get("/department-members?departmentIds=CS001").header("X-USER-ID","").header("Accept","application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("Unauthorized")));
    }
    @Test
    @DisplayName("X-USER-ID가 없음")
    void getDepartmentMembers6() throws Exception {
        mvc.perform(get("/department-members?departmentIds=CS001").header("Accept","application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.title",equalTo("Unauthorized")));
    }
}