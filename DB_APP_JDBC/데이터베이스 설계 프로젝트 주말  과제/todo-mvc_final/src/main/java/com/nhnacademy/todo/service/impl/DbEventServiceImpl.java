package com.nhnacademy.todo.service.impl;

import com.nhnacademy.todo.domain.Event;
import com.nhnacademy.todo.dto.DailyRegisterCountResponseDto;
import com.nhnacademy.todo.dto.EventCreatedResponseDto;
import com.nhnacademy.todo.dto.EventDto;
import com.nhnacademy.todo.mapper.EventMapper;
import com.nhnacademy.todo.service.EventService;
import com.nhnacademy.todo.share.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Primary
@Service
@RequiredArgsConstructor
@Transactional
public class DbEventServiceImpl implements EventService {
    private final EventMapper eventMapper;

    @Override
    public EventCreatedResponseDto insert(EventDto eventDto) {
        Event event = new Event(UserIdStore.getUserId(), eventDto.getSubject(), eventDto.getEventAt());
        eventMapper.save(event);
        return new EventCreatedResponseDto(event.getId());
    }

    @Override
    public long update(long eventId, EventDto eventDto) {
        Event target = new Event(UserIdStore.getUserId(),eventDto.getSubject(),eventDto.getEventAt());
        target.setId(eventId);
        eventMapper.update(target);
        return eventId;
    }

    @Override
    public void deleteOne(long eventId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserIdStore.getUserId());
        map.put("id",eventId);
        eventMapper.deleteById(map);
    }

    @Override
    public EventDto getEvent(long eventId) {
        Event event = eventMapper.getEvent(eventId);

        if(Objects.isNull(event)){
            return null;
        }
        return new EventDto(event.getId(),event.getSubject(),event.getEventAt());
    }

    @Override
    public List<EventDto> getEventListByMonthly(Integer year, Integer month) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserIdStore.getUserId());
        map.put("eventAt",year+"-"+month);
        List<Event> result = eventMapper.findAllByMonth(map);
        List<EventDto> eventDtos = new ArrayList<>();
        for(Event event : result){
            eventDtos.add(new EventDto(event.getId(),event.getSubject(),event.getEventAt()));
        }
        return eventDtos;
    }

    @Override
    public List<EventDto> getEventListBydaily(Integer year, Integer month, Integer day) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserIdStore.getUserId());
        map.put("eventAt",LocalDate.of(year,month,day));
        List<Event> eventList = eventMapper.findAllByDay(map);
        List<EventDto> eventDtos = new ArrayList<>();
        for(Event event : eventList){
            eventDtos.add(new EventDto(event.getId(),event.getSubject(),event.getEventAt()));
        }
        return eventDtos;
    }

    @Override
    public DailyRegisterCountResponseDto getDayliyRegisterCount(LocalDate targetDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserIdStore.getUserId());
        map.put("targetDate",targetDate);
        long count =  eventMapper.countByUserIdAndEventAt(map);
        return new DailyRegisterCountResponseDto(count);
    }

    @Override
    public void deleteEventByDaily(LocalDate eventAt) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserIdStore.getUserId());
        map.put("targetDate",eventAt);
        eventMapper.deletebyDaily(map);
    }
}
