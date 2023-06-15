package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
public class StudentsTest {
    Students csvStudents;
    Scores csvScores;
    @BeforeEach
    void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        csvStudents = context.getBean("csvStudents",Students.class);
        csvScores = context.getBean("csvScores", Scores.class);
    }
    @Test
    void load() {
        csvStudents.load();
        assertNotNull(csvStudents.findAll());
    }

    @Test
    void findAll() {
        boolean result = true;
        Student student1 = new Student(1,"A");
        Student student2 = new Student(2,"B");
        Student student3 = new Student(3,"A");
        Student student4 = new Student(4,"D");
        List<Student> expect = Arrays.asList(student1,student2,student3,student4);
        csvStudents.load();
        List<Student> actual = csvStudents.findAll().stream().collect(Collectors.toList());
        for(int i = 0; i <expect.size();i++){
            if(!(expect.get(i).getSeq()==actual.get(i).getSeq())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }

    @Test
    void merge() {
        boolean result = true;
        Student student1 = new Student(1,"A"); student1.setScore(new Score(1,30));
        Student student2 = new Student(2,"B"); student2.setScore(new Score(2,80));
        Student student3 = new Student(3,"A"); student3.setScore(new Score(3,70));
        Student student4 = new Student(4,"D");
        csvStudents.load();
        csvScores.load();
        csvStudents.merge(csvScores.findAll());
        List<Student> expect = Arrays.asList(student1,student2,student3,student4);
        List<Student> actual = csvStudents.findAll().stream().collect(Collectors.toList());
        for(int i = 0; i <expect.size();i++){
            if(!(expect.get(i).getSeq()==actual.get(i).getSeq())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }
}