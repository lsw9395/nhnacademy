package com.nhnacademy.springmvc.service;

import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import com.nhnacademy.springmvc.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public Long save(String eventAt, Event event){
        event.setId(eventRepository.getAutoIdx().incrementAndGet());
        List<Event> events = eventRepository.getEventMap().computeIfAbsent(eventAt, k -> new ArrayList<>());
        events.add(event);
        return eventRepository.getAutoIdx().get();
    }
    public void delete(Long eventId){
        for (Iterator<List<Event>> iterator = eventRepository.getEventMap().values().iterator(); iterator.hasNext(); ) {
            List<Event> list = iterator.next();
            for (Iterator<Event> eventIterator = list.iterator(); eventIterator.hasNext(); ) {
                Event event = eventIterator.next();
                if (event.getId() == eventId) {
                    eventIterator.remove();
                    return;
                }
            }
        }
    }
    public void deleteAll(String date){
        eventRepository.getEventMap().remove(date);
    }
    public List<Event> getTodoItemList(String eventAt){
        if(eventRepository.getEventMap().containsKey(eventAt)){
            return eventRepository.getEventMap().get(eventAt);
        }
        return Collections.emptyList();
    }
    public List<Event> getTodoItemListByYM(String eventAt){
        List<Event> result = new ArrayList<>();
        for(String key:eventRepository.getEventMap().keySet()){
            if(key.contains(eventAt)){
                result.addAll(eventRepository.getEventMap().get(key));
            }
        }
        return result;
    }
    public Event getEventbyid(Long eventId) throws NotExistEvent {
        for(List<Event> list:eventRepository.getEventMap().values()){
            for(Event event:list){
                if(event.getId()==eventId){
                    return event;
                }
            }
        }
        throw new NotExistEvent(eventId);
    }
}
