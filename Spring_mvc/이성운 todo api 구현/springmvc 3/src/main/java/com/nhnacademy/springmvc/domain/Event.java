package com.nhnacademy.springmvc.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String subject;
    private String eventAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    public Event(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
        this.createAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
