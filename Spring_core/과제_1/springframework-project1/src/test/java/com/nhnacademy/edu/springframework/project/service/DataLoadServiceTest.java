package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoadServiceTest {

    @Test
    public void loadAndMerge() {
        boolean result = true;
        Student student1 = new Student(1,"A"); student1.setScore(new Score(1,30));
        Student student2 = new Student(2,"B"); student2.setScore(new Score(2,80));
        Student student3 = new Student(3,"A"); student3.setScore(new Score(3,70));
        Student student4 = new Student(4,"D");

        List<Student> expect = Arrays.asList(student1,student2,student3,student4);
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        Students testStudentRepository = CsvStudents.getInstance();
        List<Student> actual = testStudentRepository.findAll().stream().collect(Collectors.toList());
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