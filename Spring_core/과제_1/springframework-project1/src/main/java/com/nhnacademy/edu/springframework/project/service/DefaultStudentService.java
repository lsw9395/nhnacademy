package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;


public class DefaultStudentService implements StudentService {

    @Override
    public Collection<Student> getPassedStudents() {
        Students studentsRepository = CsvStudents.getInstance();
        return studentsRepository.findAll()
                .stream()
                .filter(student -> student.getScore()!=null)
                .filter(student -> !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();
        return studentRepository.findAll().
                stream().
                filter(student -> student.getScore()!=null).
                sorted(Comparator.comparing(student -> student.getScore().getScore(), Comparator.reverseOrder())).
                collect(Collectors.toList());
    }
}
