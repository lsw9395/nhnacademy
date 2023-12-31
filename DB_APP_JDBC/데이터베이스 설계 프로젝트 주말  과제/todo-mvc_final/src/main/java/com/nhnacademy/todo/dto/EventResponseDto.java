package com.nhnacademy.todo.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
public class EventResponseDto {
    private long id;
    private String userId;
    private String subject;
    private LocalDate eventAt;
    private LocalDateTime createdAt;
}
