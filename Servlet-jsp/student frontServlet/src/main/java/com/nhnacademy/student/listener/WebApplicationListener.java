package com.nhnacademy.student.listener;

import com.nhnacademy.student.object.Gender;
import com.nhnacademy.student.object.MapStudentRepository;
import com.nhnacademy.student.object.Student;
import com.nhnacademy.student.object.StudentRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30
            String num = Integer.toString(new Random().nextInt(10));
            String id = "student"+num;
            String name = "아카데미"+num;
            Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];
            int age = new Random().nextInt(10)+20;
            Student student = new Student(id,name,gender,age);
            studentRepository.save(student);
        }
        context.setAttribute("students",studentRepository);
    }
}
