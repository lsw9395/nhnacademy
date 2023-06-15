package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.domain.EventbyId;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EventRepositoryId {
    private final ConcurrentMap<String, List<EventbyId>> eventMap = new ConcurrentHashMap<>();
    public void save(Event event, String userId){
        EventbyId event1 = new EventbyId(userId,event.getSubject(),event.getEventAt());
        event1.setId(event.getId());
        List<EventbyId> events = eventMap.computeIfAbsent(userId, k -> new ArrayList<>());
        events.add(event1);
    }
    public List<EventbyId> getEventbyId(String userId){
        return eventMap.get(userId);
    }
}
