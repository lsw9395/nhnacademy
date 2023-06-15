package com.nhnacademy.springbootaccount.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class SystemHealthIndicator implements HealthIndicator {

    private final AtomicBoolean health = new AtomicBoolean(true);

    @Override
    public Health health() {
        if(!health.get()){
            return Health.down().build();
        }
        return Health.up().build();
    }

    public void healthFail(){
        health.set(false);
    }

    public void healthRecovery(){
        health.set(true);
    }
}
