package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Event;
import com.nhnacademy.springmvc.exception.NotExistEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class EventRepository {
    private final ConcurrentMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
    private final AtomicLong autoIdx = new AtomicLong();

    public EventRepository() {
    }

    public ConcurrentMap<String, List<Event>> getEventMap() {
        return eventMap;
    }

    public AtomicLong getAutoIdx() {
        return autoIdx;
    }
}
