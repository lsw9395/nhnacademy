package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.*;
import com.nhnacademy.springmvc.exception.ErrorUserId;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import com.nhnacademy.springmvc.repository.EventRepository;
import com.nhnacademy.springmvc.repository.EventRepositoryId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class TodoRestController {
    private final EventRepository eventRepository;
    private final EventRepositoryId eventRepositoryId;
    @GetMapping("/events/")
    public ResponseEntity<List<Event>> getEvents(@RequestParam(value = "year",required = true)String year, @RequestParam(value = "month",required = true)String month,@RequestParam(value = "day",required = true)String day){
        log.info(year+"-"+month+"-"+day);
        List<Event> events = eventRepository.getTodoItemList(year+"-"+month+"-"+day);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping("/events")
    public Id saveEvent(@RequestBody EventDto eventDto,@RequestHeader("X-USER-ID") String userId) throws NotExistEvent {
        Long id = eventRepository.save(eventDto.getEventAt(),new Event(eventDto.getSubject(),eventDto.getEventAt()));
        eventRepositoryId.save(eventRepository.getEventbyid(id),userId);
        return new Id(id);
    }
    @GetMapping("/daily-register-count")
    public ResponseEntity<Count> countEvent(@RequestParam("date") String date){
        int count = eventRepository.getTodoItemList(date).size();
        return new ResponseEntity<>(new Count(count),HttpStatus.OK);
    }
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable("id") Long eventId){
        eventRepository.delete(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/events/daily/{date}")
    public ResponseEntity<Void> deleteEventBydate(@PathVariable("date")String date, @RequestHeader("X-USER-ID") String userId){
        eventRepository.deleteAll(date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") String ids,@RequestHeader("X-USER-ID") String userId) throws NotExistEvent {
        Long id;
        try{
            id = Long.parseLong(ids);
        } catch (NumberFormatException e){
            throw new NumberFormatException(ids);
        }
        Event event = eventRepository.getEventbyid(id);
        if(Objects.isNull(event)){
            throw new NotExistEvent(id);
        }
        List<EventbyId> eventbyIdList = eventRepositoryId.getEventbyId(userId);
        for(EventbyId eventbyId:eventbyIdList){
            if(eventbyId.getId().equals(id)){
                return new ResponseEntity<>(event,HttpStatus.OK);
            }
        }
        throw new ErrorUserId();
    }
    @PatchMapping("/events/{id}")
    public ResponseEntity<ExceptionMsg> notUseMethod(@PathVariable String id){
        ExceptionMsg msg = new ExceptionMsg(405,"Method Not Allowed / path:\"/api/calendar/events/"+id);
        return new ResponseEntity<>(msg,HttpStatus.METHOD_NOT_ALLOWED);
    }
}
