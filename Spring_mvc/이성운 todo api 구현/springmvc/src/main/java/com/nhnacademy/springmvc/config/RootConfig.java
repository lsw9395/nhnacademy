package com.nhnacademy.springmvc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.Gender;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.repository.MapStudentRepository;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackageClasses = {Base.class},excludeFilters ={@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean(name="studentRepository")
    public StudentRepository studentRepository(){
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30
            String num = Integer.toString(i);
            String id = "student"+num;
            String name = "아카데미"+num;
            Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];
            int age = new Random().nextInt(10)+20;
            Student student = new Student(id,name,gender,age);
            studentRepository.save(student);
        }
        return studentRepository;
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
