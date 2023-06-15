package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.domain.ErrorResponse;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.exception.NotSupportedContentTypeException;
import com.nhnacademy.exam.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(DuplicateDepartmentIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicateDepartmentIdException(DuplicateDepartmentIdException ex) {
        return new ErrorResponse("부서 아이디 중복 : " + ex.getDepartmentId(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return new ErrorResponse("department not found : " + ex.getDepartmentId(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
    }

    @ExceptionHandler(NotSupportedContentTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotSupportedContentTypeException(NotSupportedContentTypeException ex) {
        return new ErrorResponse("Could not find acceptable representation",HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParameterException(MissingServletRequestParameterException ex) {
        return new ErrorResponse("Required request parameter '" + ex.getParameterName() + "' for method parameter type " +
        ex.getParameterType() + " is not present",HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerUnauthorizedException(UnauthorizedException ex){
        return new ErrorResponse(ex.getMessage(),HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
    }
}
