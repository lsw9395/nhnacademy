package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentViewController {
    private final StudentRepository studentRepository;
    private final User user;
    public StudentViewController(StudentRepository studentRepository,User user){
        this.studentRepository = studentRepository;
        this.user = user;
    }
    @ModelAttribute("studentList")
    public List<Student> getStudent(){
        return studentRepository.getStudents();
    }

    @GetMapping("/view.do")
    public String view(@RequestParam String id, Model model){
        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException(id);
        }
        model.addAttribute("student",student);
        model.addAttribute("user",user);
        return "/student/view";
    }
}
