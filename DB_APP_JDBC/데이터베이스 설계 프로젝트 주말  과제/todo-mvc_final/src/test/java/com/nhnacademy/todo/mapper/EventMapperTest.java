package com.nhnacademy.todo.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nhnacademy.todo.config.RootConfig;
import com.nhnacademy.todo.domain.Event;
import com.nhnacademy.todo.dto.EventDto;
import com.nhnacademy.todo.share.UserIdStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
@Transactional
class EventMapperTest {
    @Autowired
    EventMapper eventMapper;

    @Test
    @DisplayName("save event")
    void save() {
        Event event = new Event("marco", "spring mvc", LocalDate.now());
        eventMapper.save(event);
        Assertions.assertThat(event.getId()).isNotZero();
    }

    @Test
    void update() {
        Event event = new Event("marco", "spring mvc", LocalDate.now());
        eventMapper.save(event);
        long eventId =1;
        EventDto eventDto = new EventDto(1,"spring jdbc",LocalDate.now());
        Event target = new Event("marco",eventDto.getSubject(),eventDto.getEventAt());
        target.setId(eventId);
        assertEquals(target.getId(),eventId);
    }

    @Test
    void deleteById() {
        Event event = new Event("marco", "spring mvc", LocalDate.now());
        eventMapper.save(event);
        Map<String,Object> map = new HashMap<>();
        map.put("userId", "marco");
        map.put("id",event.getId());
        eventMapper.deleteById(map);
        assertNull(eventMapper.getEvent(event.getId()));
    }

    @Test
    void getEvent() {
        Event event = new Event("marco", "spring mvc", LocalDate.now());
        eventMapper.save(event);
        assertEquals(event.getUserId(),eventMapper.getEvent(event.getId()).getUserId());
        assertEquals(event.getSubject(),eventMapper.getEvent(event.getId()).getSubject());
        assertEquals(event.getEventAt(),eventMapper.getEvent(event.getId()).getEventAt());
    }

    @Test
    void findAllByDay() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", "marco");
        map.put("targetDate",LocalDate.now());
        List<Event> eventList = eventMapper.findAllByDay(map);
        assertNotNull(eventList);
    }

    @Test
    void findAllByMonth() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", "marco");
        map.put("eventAt","2023-05");
        List<Event> eventList = eventMapper.findAllByMonth(map);
        assertNotNull(eventList);
    }

    @Test
    void countByUserIdAndEventAt() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", "marco");
        map.put("targetDate",LocalDate.now());
        assertNotNull(eventMapper.countByUserIdAndEventAt(map));
    }

    @Test
    void deletebyDaily() {
        Event event = new Event("marco", "spring mvc", LocalDate.now());
        eventMapper.save(event);
        Map<String,Object> map = new HashMap<>();
        map.put("userId", "marco");
        map.put("targetDate",LocalDate.now());
        eventMapper.deletebyDaily(map);
        assertNull(eventMapper.getEvent(event.getId()));
    }
}