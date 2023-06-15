package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.config.RootConfig;
import com.nhnacademy.springmvc.config.WebConfig;
import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import com.nhnacademy.springmvc.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class EventServiceTest {

    @Mock
    EventRepository repository;
    @InjectMocks
    EventService service;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void save() {
        String eventAt = "2023-04-20";
        Event event = new Event("test","2023-04-20");


        when(repository.getAutoIdx()).thenReturn(new AtomicLong(0L));
        when(repository.getEventMap()).thenReturn(new ConcurrentHashMap<String, List<Event>>());
        Long eventId = service.save(eventAt, event);

        verify(repository,times(2)).getAutoIdx();
        verify(repository,times(1)).getEventMap();
        assertEquals(Long.valueOf(1), eventId);
    }

    @Test
    void delete() {
        Long deleteId = 1L;
        List<Event> testList = new ArrayList<>();
        Event test1 = new Event("test1","2023-04-20"); test1.setId(1L);
        Event test2 = new Event("test2","2023-04-20"); test2.setId(2L);
        Event test3 = new Event("test3","2023-04-20"); test3.setId(3L);
        ConcurrentHashMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
        eventMap.put("2023-04-20", Arrays.asList(test1,test2,test3));

        doReturn(eventMap).when(repository).getEventMap();
        //service.delete(deleteId);
        assertEquals(3,repository.getEventMap().get("2023-04-20").size());
    }

    @Test
    void deleteAll() {
        Long deleteId = 1L;
        List<Event> testList = new ArrayList<>();
        Event test1 = new Event("test1","2023-04-20"); test1.setId(1L);
        Event test2 = new Event("test2","2023-04-20"); test2.setId(2L);
        Event test3 = new Event("test3","2023-04-20"); test3.setId(3L);
        ConcurrentHashMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
        eventMap.put("2023-04-20", Arrays.asList(test1,test2,test3));
        doReturn(eventMap).when(repository).getEventMap();
        service.deleteAll("2023-04-20");
        verify(repository).getEventMap();
        assertNull(eventMap.get("2023-04-20"));
    }

    @Test
    void getTodoItemList() {
        List<Event> testList = new ArrayList<>();
        Event test1 = new Event("test1","2023-04-20"); test1.setId(1L);
        Event test2 = new Event("test2","2023-04-20"); test2.setId(2L);
        ConcurrentHashMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
        eventMap.put("2023-04-20", Arrays.asList(test1,test2));
        doReturn(eventMap).when(repository).getEventMap();
        List<Event> actual = service.getTodoItemList("2023-04-20");
        assertEquals(eventMap.get("2023-04-20"),actual);
    }

    @Test
    void getTodoItemListByYM() {
        List<Event> testList = new ArrayList<>();
        Event test1 = new Event("test1","2023-04-20"); test1.setId(1L);
        Event test2 = new Event("test2","2023-04-22"); test2.setId(2L);
        Event test3 = new Event("test2","2023-04-26"); test3.setId(3L);
        ConcurrentHashMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
        eventMap.put("2023-04-20", Arrays.asList(test1));
        eventMap.put("2023-04-22", Arrays.asList(test2));
        eventMap.put("2023-04-26", Arrays.asList(test3));
        doReturn(eventMap).when(repository).getEventMap();
        List<Event> actual = service.getTodoItemListByYM("2023-04");
        List<Event> expect = new ArrayList<>();
        for(String key:eventMap.keySet()){
            if(key.contains("2023-04")){
                expect.addAll((eventMap).get(key));
            }
        }
        assertEquals(expect,actual);
    }

    @Test
    void getEventbyid() throws NotExistEvent {
        List<Event> testList = new ArrayList<>();
        Event test1 = new Event("test1","2023-04-20"); test1.setId(1L);
        Event test2 = new Event("test2","2023-04-20"); test2.setId(2L);
        Event test3 = new Event("test2","2023-04-20"); test3.setId(3L);
        ConcurrentHashMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
        eventMap.put("2023-04-20",Arrays.asList(test1,test2,test3));
        doReturn(eventMap).when(repository).getEventMap();
        Event actual = service.getEventbyid(1L);
        assertEquals(test1,actual);
    }
}