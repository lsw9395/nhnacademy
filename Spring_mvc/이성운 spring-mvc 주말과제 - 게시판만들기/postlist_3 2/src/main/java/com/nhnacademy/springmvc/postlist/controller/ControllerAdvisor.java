package com.nhnacademy.springmvc.postlist.controller;

import com.nhnacademy.springmvc.postlist.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, Model model){
        String message = "현재 이'"+ex.getParameterName()+"'이 입력되지 않았습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NotFoundPost.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundPost(NotFoundPost ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 페이지가 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NotFoundPostId.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundPostId(NotFoundPostId ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 게시글이 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NotFoundUser.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundUser(NotFoundUser ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 페이지가 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NotFoundUserId.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundUserId(NotFoundUserId ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 유저가 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNumberFormatException(NumberFormatException ex,Model model){
        String message = "Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string:"+ex.getMessage();
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex,Model model){
        String message = ex.getRequestURL()+"주소를 찾을 수 없습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadyExists(UserAlreadyExists ex,Model model){
        String message = "해당"+ex.getMessage()+"은 이미 존재합니다.";
        model.addAttribute("message",message);
        return "/error";
    }
}
