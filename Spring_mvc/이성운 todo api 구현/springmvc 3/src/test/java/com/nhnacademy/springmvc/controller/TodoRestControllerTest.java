package com.nhnacademy.springmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.config.RootConfig;
import com.nhnacademy.springmvc.config.WebConfig;
import com.nhnacademy.springmvc.domain.EventDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
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
class TodoRestControllerTest {
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
    @DisplayName("이벤트 등록")
    void saveEvent() throws Exception {
        EventDto ed = new EventDto();
        ReflectionTestUtils.setField(ed,"subject","sleep");
        ReflectionTestUtils.setField(ed,"eventAt","2023-04-20");
        mockMvc.perform(post("/api/calendar/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-USER-ID","marco")
                        .content(objectMapper.writeValueAsString(ed)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회")
    void getEvents() throws Exception {
        mockMvc.perform(get("/api/calendar/events/?year=2023&month=04&day=20")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].subject").value("sleep"))
                .andExpect(jsonPath("$[0].eventAt").value("2023-04-20"))
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 월별 조회")
    void getEventsByYB() throws Exception {
        mockMvc.perform(get("/api/calendar/events/?year=2023&month=04")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].subject").value("sleep"))
                .andExpect(jsonPath("$[0].eventAt").value("2023-04-01"))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].subject").value("sleep"))
                .andExpect(jsonPath("$[1].eventAt").value("2023-04-20"))
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회 아이디 누락")
    void getEvents2() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회 잘못된 이벤트 소유자")
    void getEvent3() throws Exception{
        mockMvc.perform(get("/api/calendar/events/1")
                        .header("X-USER-ID","lee"))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회 존재하지 않음")
    void getEvent4() throws Exception{
        mockMvc.perform(get("/api/calendar/events/10000")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회 500error")
    void getEvent5() throws Exception{
        mockMvc.perform(get("/api/calendar/events/a")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }
    @Test
    @DisplayName("이벤트 조회 파라미터 누락")
    void getEvent6() throws Exception {
        mockMvc.perform(get("/api/calendar/events/?year=2023")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    @DisplayName("일별 이벤트 갯수 카운트")
    void countEvent() throws Exception {
        mockMvc.perform(get("/api/calendar/daily-register-count?date=2023-04-20")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").isNumber())
                .andReturn();
    }

    @Test
    @DisplayName("아이디로 이벤트 조회")
    void getEventById() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.userId").value("marco"))
                .andExpect(jsonPath("$.subject").value("test"))
                .andExpect(jsonPath("$.eventDt").value("2023-04-01"))
                .andReturn();
    }

    @Test
    @DisplayName("아이디로 이벤트 삭제")
    void deleteEventById() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/2")
                .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @DisplayName("날짜로 이벤트 삭제")
    void deleteEventBydate() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/daily/2023-04-20")
                        .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
    @Test
    @DisplayName("사용하지 않는 메소드 사용")
    void notUseMethod() throws Exception {
        mockMvc.perform(patch("/api/calendar/events/1")
                .header("X-USER-ID","marco"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }
}