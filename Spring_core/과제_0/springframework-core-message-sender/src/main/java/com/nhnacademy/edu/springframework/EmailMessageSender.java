package com.nhnacademy.edu.springframework;

public class EmailMessageSender implements MessageSender{
    @Override
    public boolean sendMessage(User user, String message) {
        System.out.println("Email Message Sent "+user.getEmail()+" : "+message);
        return true;
    }
}
