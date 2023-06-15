package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
        DataLoadService loadService = context.getBean("csvDataLoadService", DataLoadService.class);
        loadService.loadAndMerge();



        StudentService studentService = context.getBean("defaultStudentService",StudentService.class);
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        GradeQueryService gradeQueryService = context.getBean("defaultGradeQueryService",GradeQueryService.class);
        List<Score> scoreByStudentName = gradeQueryService.getScoreByStudentName("A");
        System.out.println(scoreByStudentName);

        Score score =gradeQueryService.getScoreByStudentSeq(11);
        System.out.println(score);
    }
}
