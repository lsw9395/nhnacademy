package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.exception.DuplicateStudentIdException;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }
    @ExceptionHandler(NoHandlerFoundException.class )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound(){
        log.info("404 not found");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServlerError(Exception e, Model model, HttpServletRequest request){
        model.addAttribute("exception",e.getMessage());
        log.info("internal server error");
        return "error/500";
    }
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentNotFound(StudentNotFoundException studentNotFoundException){
        log.info("error:{}",studentNotFoundException.getMessage(),studentNotFoundException);
        return "error/studentNotFound";
    }

    @ExceptionHandler(DuplicateStudentIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String duplicateStudentId(DuplicateStudentIdException duplicateStudentIdException){
        return "error/duplicateStudentId";
    }
        @ExceptionHandler(value = {
            BindException.class
    })
    public String validException(Model model, BindException bindException){
        List<String> errorMsgList = new ArrayList<>();
        for(FieldError fieldError  : bindException.getFieldErrors()){
            StringBuilder sb = new StringBuilder();
            sb.append("[" + fieldError.getField() +  "]");
            sb.append( fieldError.getDefaultMessage());
            sb.append("[input]" + fieldError.getRejectedValue());
            errorMsgList.add(sb.toString());
        }
        model.addAttribute("errorMsgList", errorMsgList);
        return "error/valid";
    }
}
