package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.ExceptionMsg;
import com.nhnacademy.springmvc.exception.ErrorUserId;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionMsg> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        ExceptionMsg message = new ExceptionMsg(400, "Required request parameter'"+ex.getParameterName()+"' for method parameter type Integer is not present");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ErrorUserId.class,NullPointerException.class})
    public ResponseEntity<ExceptionMsg> handleErrorUserId(){
        ExceptionMsg message = new ExceptionMsg(403,"잘못된 이벤트 소유자");
        return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ExceptionMsg> handleMissingRequestHeaderException(){
        ExceptionMsg message = new ExceptionMsg(401,"Unauthorized");
        return new ResponseEntity<>(message,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NotExistEvent.class)
    public ResponseEntity<ExceptionMsg> handleNotFoundEvent(NotExistEvent ex){
        ExceptionMsg message = new ExceptionMsg(404,"이벤트가 존재하지 않습니다."+ex.getMessage());
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ExceptionMsg> handleNumberFormatException(NumberFormatException ex){
        ExceptionMsg message = new ExceptionMsg(500,"Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string:"+ex.getMessage());
        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionMsg> handleNoHandlerFoundException(NoHandlerFoundException ex){
        ExceptionMsg msg = new ExceptionMsg(404,  ex.getRequestURL()+"주소를 찾을 수 없습니다.");
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }
}
