package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class EventRepository {
    private final ConcurrentMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
    private final AtomicLong autoIdx = new AtomicLong();
    public Long save(String eventAt,Event event){
        event.setId(autoIdx.incrementAndGet());
        log.info(String.valueOf(autoIdx.get()));
        List<Event> events = eventMap.computeIfAbsent(eventAt, k -> new ArrayList<>());
        events.add(event);
        return autoIdx.get();
    }
    public void delete(Long eventId){
        for(List<Event> list:eventMap.values()){
            for(Event event:list){
                if(event.getId()==eventId){
                    list.remove(event);
                    return;
                }
            }
        }
    }
    public void deleteAll(String date){
        eventMap.remove(date);
    }
    public List<Event> getTodoItemList(String eventAt){
        if(eventMap.containsKey(eventAt)){
            return eventMap.get(eventAt);
        }
        return Collections.emptyList();
    }
    public Event getEventbyid(Long eventId) throws NotExistEvent {
        for(List<Event> list:eventMap.values()){
            for(Event event:list){
                if(event.getId()==eventId){
                    return event;
                }
            }
        }
        throw new NotExistEvent(eventId);
    }
}
