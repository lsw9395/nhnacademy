package com.nhnacademy.springmvc.postlist.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvc.postlist.Base;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.repository.*;

import com.nhnacademy.springmvc.postlist.service.PostService;

import com.nhnacademy.springmvc.postlist.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;


import java.time.LocalDateTime;

@Configuration
@ComponentScan(basePackageClasses = {Base.class}, excludeFilters ={@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public UserService userService(){
        UserService userService = new UserService(userRepository());
        for(int i = 0; i<=100;i++){
            userService.add(new User("user"+i,""+i,"유저"+i,null));
        }
        return userService;
    }
    @Bean
    public UserRepository userRepository(){
        return new JsonUserRepository();
    }
    @Bean
    public PostService postService(){
        PostService postService = new PostService(postRepository());
        Post post1 = new Post(1,"asd","ASdf","user1", LocalDateTime.now(),0);
        Post post2 = new Post(2,"asd","ASdf","user2", LocalDateTime.now(),0);
        Post post3 = new Post(3,"asd","ASdf","user3", LocalDateTime.now(),0);
        post1.setId(postService.register(post1));
        post2.setId(postService.register(post2));
        post3.setId(postService.register(post3));
        return postService;
    }
    @Bean
    public PostRepository postRepository(){
        return new JsonPostRepository();
    }
    @Bean
    public User admin(){
        return new User("admin","12345","관리자",null);
    }


    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ObjectMapper objectMapper() {
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
