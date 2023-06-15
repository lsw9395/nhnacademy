package com.nhnacademy.todo.service.impl;

import com.nhnacademy.todo.domain.Event;
import com.nhnacademy.todo.dto.DailyRegisterCountResponseDto;
import com.nhnacademy.todo.dto.EventCreatedResponseDto;
import com.nhnacademy.todo.dto.EventDto;
import com.nhnacademy.todo.exception.InvalidEventOwnerException;
import com.nhnacademy.todo.mapper.EventMapper;
import com.nhnacademy.todo.repository.EventRepository;
import com.nhnacademy.todo.service.EventService;
import com.nhnacademy.todo.share.UserIdStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DbEventServiceImplTest {

    private static EventService eventService;
    private static EventMapper eventMapper;
    private static MockedStatic<UserIdStore> userIdStore;


    @BeforeAll
    static void  setUp(){
        eventMapper = mock(EventMapper.class);
        userIdStore = mockStatic(UserIdStore.class);
        eventService = new DbEventServiceImpl(eventMapper);
    }
    @Test
    void insert() {
        EventDto eventDto = EventDto.builder()
                .subject("java study")
                .eventAt(LocalDate.now())
                .build();
        Event event = new Event("marco","java study", LocalDate.now());
        ReflectionTestUtils.setField(event,"id",100l);
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        doNothing().when(eventMapper).save(any());

        //when(실행)
        eventService.insert(eventDto);

        verify(eventMapper,times(1)).save(any());
    }

    @Test
    void update() {
//given
        EventDto eventDto = EventDto.builder()
                .subject("spring study")
                .eventAt(LocalDate.now())
                .build();
        Event event = new Event("marco","spring study", LocalDate.now());
        ReflectionTestUtils.setField(event,"id",100l);
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        doNothing().when(eventMapper).update(any());

        eventService.update(100l,eventDto);

        verify(eventMapper,times(1)).update(any());
    }


    @Test
    void getEvent() {
        //given
        Event event = new Event("marco","spring study", LocalDate.now());
        ReflectionTestUtils.setField(event,"id",100l);
        when(eventMapper.getEvent(100l)).thenReturn(event);
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");

        //when
        EventDto eventDto = eventService.getEvent(100l);

        //then
        Assertions.assertThat(eventDto.getId()).isEqualTo(100l);
        Assertions.assertThat(eventDto.getSubject()).isEqualTo("spring study");
        Assertions.assertThat(eventDto.getEventAt()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("event is null")
    void getEvent_notFound(){
        //given
        when(eventMapper.getEvent(anyLong())).thenReturn(null);
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");

        //when
        EventDto eventDto = eventService.getEvent(100l);
        Assertions.assertThat(eventDto).isNull();
    }
    @Test
    @DisplayName("event 삭제")
    void deleteOne() {
        //given
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        doNothing().when(eventMapper).deleteById(anyMap());

        //when
        eventService.deleteOne(100l);

        //then
        verify(eventMapper, atMostOnce()).deleteById(anyMap());

    }
    @Test
    void getEventListByMonthly() {
        //given
        List<Event> eventList = List.of(
                new Event("marco","spring study1",LocalDate.now()),
                new Event("marco","spring study2",LocalDate.now()),
                new Event("marco","spring study3",LocalDate.now())
        );

        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        when(eventMapper.findAllByMonth(anyMap())).thenReturn(eventList);

        //when
        List<EventDto> eventDtos = eventService.getEventListByMonthly(LocalDate.now().getYear(),LocalDate.now().getMonthValue());

        //then
        Assertions.assertThat(eventDtos).hasSize(3);
        Assertions.assertThat(eventDtos.get(0).getSubject()).isEqualTo("spring study1");
        Assertions.assertThat(eventDtos.get(1).getSubject()).isEqualTo("spring study2");
        Assertions.assertThat(eventDtos.get(2).getSubject()).isEqualTo("spring study3");

    }

    @Test
    void getEventListBydaily() {

        //given
        List<Event> eventList = List.of(
                new Event("marco","spring study1",LocalDate.now()),
                new Event("marco","spring study2",LocalDate.now()),
                new Event("marco","spring study3",LocalDate.now())
        );

        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        when(eventMapper.findAllByDay(anyMap())).thenReturn(eventList);

        //when
        List<EventDto> eventDtos = eventService.getEventListBydaily(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());


        //then
        Assertions.assertThat(eventDtos).hasSize(3);
        Assertions.assertThat(eventDtos.get(0).getSubject()).isEqualTo("spring study1");
        Assertions.assertThat(eventDtos.get(1).getSubject()).isEqualTo("spring study2");
        Assertions.assertThat(eventDtos.get(2).getSubject()).isEqualTo("spring study3");

    }

    @Test
    void getDayliyRegisterCount() {
        //given
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        when(eventMapper.countByUserIdAndEventAt(anyMap())).thenReturn(3l);

        //when
        DailyRegisterCountResponseDto dailyRegisterCountResponseDto = eventService.getDayliyRegisterCount(LocalDate.now());

        //then
        Assertions.assertThat(dailyRegisterCountResponseDto.getCount()).isEqualTo(3l);
    }

    @Test
    void deleteEventByDaily() {
        //given
        userIdStore.when(UserIdStore::getUserId).thenReturn("marco");
        doNothing().when(eventMapper).deletebyDaily(anyMap());

        //when
        eventService.deleteEventByDaily(LocalDate.now());

        //then
        verify(eventMapper,atMostOnce()).deletebyDaily(anyMap());

    }
}