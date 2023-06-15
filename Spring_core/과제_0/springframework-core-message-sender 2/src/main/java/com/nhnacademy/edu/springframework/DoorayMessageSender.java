package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.edu.springframework.annotation.ElapsedTimeLog;

public class DoorayMessageSender implements MessageSender{
    private final DoorayHookSender doorayHookSender;
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