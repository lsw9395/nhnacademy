package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.edu.springframework.annotation.ElapsedTimeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoorayMessageSender implements MessageSender{
    private final DoorayHookSender doorayHookSender;
    @Autowired
    public DoorayMessageSender(DoorayHookSender doorayHookSender){
        this.doorayHookSender = doorayHookSender;
    }
    @Override
    @ElapsedTimeLog
    public boolean sendMessage(User user, String message) {
        doorayHookSender.send(DoorayHook.builder().
                botName(user.getName()).
                text(message).
                build());
        return true;
    }
}