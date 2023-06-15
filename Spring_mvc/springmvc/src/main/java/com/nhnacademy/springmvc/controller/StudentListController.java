package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentListController {
    private final StudentRepository studentRepository;
    private final User user;
    public StudentListController(StudentRepository studentRepository,User user){
        this.studentRepository = studentRepository;
        this.user = user;
    }
    @ModelAttribute("studentList")
    public List<Student> getStudent(){
        return studentRepository.getStudents();
    }
    @GetMapping("/list.do")
    public String viewList(@ModelAttribute List<Student> studentList, Model model){
        model.addAttribute("studentList",studentList);
        model.addAttribute("user",user);
        return "/student/list";
    }
}
