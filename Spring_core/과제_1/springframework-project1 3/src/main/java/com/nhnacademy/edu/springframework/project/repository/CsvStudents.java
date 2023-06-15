package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class CsvStudents implements Students {
    private static boolean isLoad = false;
    private final static List<Student> studentList = new ArrayList<>();
    private static void checkLoaded() {
        if (!isLoad) {
            throw new IllegalStateException("아직 데이터 업로드 안됨");
        }
    }

    @Override
    public void load() {
        if(!isLoad){
            try(InputStream inputStream = new ClassPathResource("data/student.csv").getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
                String line;
                while((line = reader.readLine())!=null){
                    String lines[] = line.split(",");
                    Student student = new Student(Integer.parseInt(lines[0]),lines[1]);
                    studentList.add(student);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                isLoad = true;
            }
        }
    }

    @Override
    public Collection<Student> findAll() {
        checkLoaded();
        return studentList;
    }
    @Override
    public void merge(Collection<Score> scores) {
        checkLoaded();
        for(Student student:studentList){
            for(Score score:scores){
                if(score.getStudentSeq()==student.getSeq()){
                    student.setScore(score);
                }
            }
        }
    }
}
