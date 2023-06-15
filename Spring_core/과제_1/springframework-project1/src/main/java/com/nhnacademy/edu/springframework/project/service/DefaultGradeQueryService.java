package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {


    @Override
    public List<Score> getScoreByStudentName(String name) {
        Collection<Student> students = CsvStudents.getInstance().findAll();
        Map<String,List<Student>> scoresByname = students.stream().collect(Collectors.groupingBy(student -> student.getName()));
        List<Score> scoreList = scoresByname.get(name).stream().map(Student::getScore).collect(Collectors.toList());
        return scoreList;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Collection<Student> students = CsvStudents.getInstance().findAll();
        Student student1 = students.stream().filter(student -> student.getSeq()==seq).findFirst().orElse(null);
        return student1.getScore();
    }
}
