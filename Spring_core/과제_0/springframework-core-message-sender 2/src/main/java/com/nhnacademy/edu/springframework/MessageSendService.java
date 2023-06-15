package com.nhnacademy.edu.springframework;

public class MessageSendService {
    private final MessageSender messageSender;


    public MessageSendService(MessageSender messageSender){
        this.messageSender=messageSender;
    }
    public void doSendMessage(){
        User user = new User("이성운","dltjddns93@gmail.com","010-9395-5684");
        String message = "bean+aop+properties";
        messageSender.sendMessage(user,message);
    }
}