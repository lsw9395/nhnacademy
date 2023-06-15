package com.nhnacademy.springmvc.exception;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 22/02/2023
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
