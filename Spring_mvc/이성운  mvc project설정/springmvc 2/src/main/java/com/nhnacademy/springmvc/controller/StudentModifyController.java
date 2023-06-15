package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentModifyController {
    private final StudentRepository studentRepository;
    private final User user;
    public StudentModifyController(StudentRepository studentRepository, User user){
        this.studentRepository = studentRepository;
        this.user=user;
    }
    @ModelAttribute("studentList")
    public List<Student> getStudent(){
        return studentRepository.getStudents();
    }

    @PostMapping("/update.do")
    public String modify(@Validated @ModelAttribute StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        Student student = new Student(studentRegisterRequest.getId(),studentRegisterRequest.getName(),studentRegisterRequest.getGender(),studentRegisterRequest.getAge());
        studentRepository.update(student);
        return "redirect:/student/view.do?id="+student.getId();
    }
    @GetMapping("/update.do")
    public String view(@RequestParam String id, Model model){
        Student student = studentRepository.getStudentById(id);
        model.addAttribute("student",student);
        model.addAttribute("user",user);
        return "/student/register";
    }
}
