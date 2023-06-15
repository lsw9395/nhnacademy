package com.nhnacademy.edu.springframework.project.service;


import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class DefaultStudentService implements StudentService {
    private final Students csvStudents;
    public DefaultStudentService(Students csvStudents){
        this.csvStudents =csvStudents;
    }
    @Override
    public Collection<Student> getPassedStudents() {
        return csvStudents.findAll()
                .stream()
                .filter(student -> student.getScore()!=null)
                .filter(student -> !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        return csvStudents.findAll().
                stream().
                filter(student -> student.getScore()!=null).
                sorted(Comparator.comparing(student -> student.getScore().getScore(), Comparator.reverseOrder())).
                collect(Collectors.toList());
    }
}
