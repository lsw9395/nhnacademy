package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.controller.ControllerBase;
import com.nhnacademy.springmvc.domain.Gender;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.MapStudentRepository;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Random;

@EnableWebMvc
@Configuration
//@ComponentScan(basePackages = {"com.nhnacademy.springmvc.**.controller"})
@ComponentScan(basePackageClasses = {com.nhnacademy.springmvc.controller.ControllerBase.class})
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/login");
    }

    @Bean(name="admin")
    public User admin(){
        return new User("admin","admin","1234");
    }
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
}
