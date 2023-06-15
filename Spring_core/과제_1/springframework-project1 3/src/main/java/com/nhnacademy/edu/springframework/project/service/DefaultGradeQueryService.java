package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private final Students csvStudents;
    @Autowired
    public DefaultGradeQueryService(@Qualifier("csvStudents") Students csvStudents){
        this.csvStudents=csvStudents;
    }
    @Override
    public List<Score> getScoreByStudentName(String name) {
        Collection<Student> students = csvStudents.findAll();
        Map<String,List<Student>> scoresByname = students.stream().collect(Collectors.groupingBy(student -> student.getName()));
        List<Score> scoreList = scoresByname.get(name).stream().map(Student::getScore).collect(Collectors.toList());
        return scoreList;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Collection<Student> students = csvStudents.findAll();
        Student student1 = students.stream().filter(student -> student.getSeq()==seq).findFirst().orElse(null);
        return student1.getScore();
    }
}
