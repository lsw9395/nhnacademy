package com.nhnacademy.springmvc.domain;

public class EventDto {
    String subject;
    String eventAt;
    public EventDto(){

    }
    public EventDto(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEventAt() {
        return eventAt;
    }

    public void setEventAt(String eventAt) {
        this.eventAt = eventAt;
    }
}
