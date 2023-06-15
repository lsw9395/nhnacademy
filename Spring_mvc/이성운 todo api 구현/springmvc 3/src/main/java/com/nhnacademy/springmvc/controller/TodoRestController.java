package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.*;
import com.nhnacademy.springmvc.exception.ErrorUserId;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import com.nhnacademy.springmvc.repository.EventRepository;
import com.nhnacademy.springmvc.repository.EventRepositoryId;
import com.nhnacademy.springmvc.service.EventService;
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
    private final EventService service;
    private final EventRepositoryId eventRepositoryId;
    @GetMapping("/events/")
    public ResponseEntity<List<Event>> getEvents(@RequestParam(value = "year",required = true)String year, @RequestParam(value = "month",required = true)String month, @RequestParam(value = "day", required = false) String day,@RequestHeader("X-USER-ID") String userId){
        log.info(year+"-"+month+"-"+day);
        if(Objects.isNull(day)){
            List<Event> events = service.getTodoItemListByYM(year+"-"+month);
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        List<Event> events = service.getTodoItemList(year+"-"+month+"-"+day);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<Id> saveEvent(@RequestBody EventDto eventDto,@RequestHeader("X-USER-ID") String userId) throws NotExistEvent {
        Long id = service.save(eventDto.getEventAt(),new Event(eventDto.getSubject(),eventDto.getEventAt()));
        eventRepositoryId.save(service.getEventbyid(id),userId);
        return new ResponseEntity<>(new Id(id),HttpStatus.CREATED);
    }
    @GetMapping("/daily-register-count")
    public ResponseEntity<Count> countEvent(@RequestParam("date") String date){
        int count = service.getTodoItemList(date).size();
        return new ResponseEntity<>(new Count(count),HttpStatus.OK);
    }
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable("id") Long eventId){
        service.delete(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/events/daily/{date}")
    public ResponseEntity<Void> deleteEventBydate(@PathVariable("date")String date, @RequestHeader("X-USER-ID") String userId){
        service.deleteAll(date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/events/{id}")
    public ResponseEntity<EventbyId> getEventById(@PathVariable("id") String ids,@RequestHeader("X-USER-ID") String userId) throws NotExistEvent {
        Long id;
        try{
            id = Long.parseLong(ids);
        } catch (NumberFormatException e){
            throw new NumberFormatException(ids);
        }
        Event event = service.getEventbyid(id);
        if(Objects.isNull(event)){
            throw new NotExistEvent(id);
        }
        List<EventbyId> eventbyIdList = eventRepositoryId.getEventbyId(userId);
        for(EventbyId eventbyId:eventbyIdList){
            if(eventbyId.getId().equals(id)){
                return new ResponseEntity<>(eventbyId,HttpStatus.OK);
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
