package com.nhnacademy.springmvc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.repository.EventRepository;
import com.nhnacademy.springmvc.repository.EventRepositoryId;
import com.nhnacademy.springmvc.service.EventService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = {Base.class},excludeFilters ={@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean("eventRepository")
    public EventRepository eventRepository(){
        return new EventRepository();
    }
    @Bean("eventService")
    public EventService eventService(){
        EventService service = new EventService(eventRepository());
        service.save("2023-04-01",new Event("sleep","2023-04-01"));
        return service;
    }
    @Bean("eventRepositoryId")
    public EventRepositoryId eventRepositoryId(){
        EventRepositoryId eventRepository = new EventRepositoryId();
        Event event = new Event("test","2023-04-01");
        event.setId(1L);
        eventRepository.save(event,"marco");
        return eventRepository;
    }
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public ObjectMapper ObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //pretty print json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //value -> null 무시
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //LocalDate, LocalDateTime support jsr310
        objectMapper.registerModule(new JavaTimeModule());
        //timestamp 출력을 disable. -> 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
