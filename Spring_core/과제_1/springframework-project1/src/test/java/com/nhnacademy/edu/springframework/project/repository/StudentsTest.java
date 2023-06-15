package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StudentsTest {

    @Test
    void load() {
        try(InputStream inputStream = new ClassPathResource("data/student.csv").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
            String line;
            while((line = reader.readLine())!=null){
                String lines[] = line.split(",");
                Student student = new Student(Integer.parseInt(lines[0]),lines[1]);
                System.out.println(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll() {
        boolean result = true;
        Student student1 = new Student(1,"A");
        Student student2 = new Student(2,"B");
        Student student3 = new Student(3,"A");
        Student student4 = new Student(4,"D");
        List<Student> expect = Arrays.asList(student1,student2,student3,student4);
        Students csvStudents = CsvStudents.getInstance();
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
        Students csvStudents = CsvStudents.getInstance();
        Scores csvScores = CsvScores.getInstance();
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