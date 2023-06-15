package com.nhnacademy.springmvc.exception;

public class NotExistEvent extends Exception{
    public NotExistEvent(Long id) {super("eventId:"+id);}
}
