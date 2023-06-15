package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {
    private final MessageSender messageSender;


    @Autowired
    public MessageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender){
        this.messageSender=messageSender;
    }
    public void doSendMessage(){
        User user = new User("이성운","dltjddns93@gmail.com","010-9395-5684");
        String message = "Stereotype+aop+properties";
        messageSender.sendMessage(user,message);
    }
}