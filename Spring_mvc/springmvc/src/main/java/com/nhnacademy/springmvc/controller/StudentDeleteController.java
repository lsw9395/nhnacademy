package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentDeleteController {
    private final StudentRepository studentRepository;
    public StudentDeleteController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @ModelAttribute("studentList")
    public List<Student> getStudent(){
        return studentRepository.getStudents();
    }

    @PostMapping("/delete.do")
    public String deleteStd(@RequestParam String id){
        studentRepository.deleteById(id);
        return "redirect:/student/list.do";
    }
}
