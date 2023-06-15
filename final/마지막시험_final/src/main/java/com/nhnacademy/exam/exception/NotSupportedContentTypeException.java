package com.nhnacademy.exam.exception;

public class NotSupportedContentTypeException extends Exception{
    public NotSupportedContentTypeException() {
        super("Could not find acceptable representation");
    }

    public NotSupportedContentTypeException(String message) {
        super(message);
    }
}
