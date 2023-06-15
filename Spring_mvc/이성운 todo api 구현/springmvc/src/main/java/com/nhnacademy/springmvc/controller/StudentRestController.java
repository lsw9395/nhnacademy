package com.nhnacademy.springmvc.controller;


import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.DuplicateStudentIdException;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentRestController {
    private final StudentRepository studentRepository;

    @PostMapping({"/",""})
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if(studentRepository.existById(studentRegisterRequest.getId())){
            throw new DuplicateStudentIdException(studentRegisterRequest.getId());
        }
        Student student = new Student(studentRegisterRequest.getId(),studentRegisterRequest.getName(),studentRegisterRequest.getGender(),studentRegisterRequest.getAge());
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId){
        Student student = studentRepository.getStudentById(studentId);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException(studentId);
        }
        ResponseEntity<Student> studentResponseEntity = new ResponseEntity<>(student,HttpStatus.OK);
        return studentResponseEntity;
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<Void> modifyStudent(@PathVariable("studentId") String studentId, @Valid @RequestBody StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult){
        Student student = studentRepository.getStudentById(studentId);
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if(Objects.isNull(student)){
            throw new StudentNotFoundException(studentId);
        }
        //student.setId(studentRegisterRequest.getId());
        student.setName(studentRegisterRequest.getName());
        student.setGender(studentRegisterRequest.getGender());
        student.setAge(studentRegisterRequest.getAge());
        //studentRepository.update(student);
        return new ResponseEntity(HttpStatus.OK);
    }
}
