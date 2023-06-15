package com.nhnacademy.edu.springframework.project.service;


import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class StudentServiceTest {
    @Test
    public void getPassedStudents() {
        boolean result = true;
        Student student2 = new Student(2,"B"); student2.setScore(new Score(2,80));
        Student student3 = new Student(3,"A"); student3.setScore(new Score(3,70));

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        DefaultStudentService service = new DefaultStudentService();
        List<Student> expect = Arrays.asList(student2,student3);
        List<Student> actual = service.getPassedStudents().stream().collect(Collectors.toList());
        for(int i = 0 ; i <expect.size();i++){
            if(!(expect.get(i).getSeq() == actual.get(i).getSeq())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }

    @Test
    public void getStudentsOrderByScore() {
        boolean result = true;
        Student student1 = new Student(1,"A"); student1.setScore(new Score(1,30));
        Student student2 = new Student(2,"B"); student2.setScore(new Score(2,80));
        Student student3 = new Student(3,"A"); student3.setScore(new Score(3,70));

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        DefaultStudentService service = new DefaultStudentService();

        List<Student> expect = Arrays.asList(student2,student3,student1);
        List<Student> actual = service.getStudentsOrderByScore().stream().collect(Collectors.toList());
        for(int i = 0 ; i <expect.size();i++){
            if(!(expect.get(i).getSeq() == actual.get(i).getSeq())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }
}