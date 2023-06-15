package com.nhnacademy.exam.exception;

public class UnauthorizedException extends Exception{
    public UnauthorizedException() {
        super("Unauthorized");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
