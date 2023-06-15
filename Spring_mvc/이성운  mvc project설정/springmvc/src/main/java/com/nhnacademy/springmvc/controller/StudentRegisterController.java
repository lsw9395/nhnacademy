package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.validator.EnumPatternValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentRegisterController {
    private final StudentRepository studentRepository;
    private final User user;
    public StudentRegisterController(StudentRepository studentRepository,User user){
        this.studentRepository = studentRepository;
        this.user = user;
    }
    @ModelAttribute("studentList")
    public List<Student> getStudent(){
        return studentRepository.getStudents();
    }

    @PostMapping("/register.do")
    public String modify(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("exception",new ValidationFailedException(bindingResult));
            return "error";
        }
        Student student2 = studentRepository.getStudentById(studentRegisterRequest.getId());
        if(Objects.nonNull(student2)){
            model.addAttribute("exception","아이디가 존재합니다.");
            return "error";
        }
        Student student = new Student(studentRegisterRequest.getId(),studentRegisterRequest.getName(),studentRegisterRequest.getGender(),studentRegisterRequest.getAge());
        studentRepository.save(student);
        return "redirect:/student/view.do?id="+student.getId();
    }
    @GetMapping("/register.do")
    public String view(Model model){
        model.addAttribute("user",user);
        return "/student/register";
    }
}
