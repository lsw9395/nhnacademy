package com.nhnacademy.springbootaccount;

import com.nhnacademy.springbootaccount.actuator.SystemHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    SystemHealthIndicator healthIndicator;
    @PostMapping("/management/fail")
    public String healthFail(){
        healthIndicator.healthFail();
        return "{\"result\":\"fail\"}";
    }
    @PostMapping("/management/recovery")
    public String healthRecovery(){
        healthIndicator.healthRecovery();
        return "{\"result\":\"recovery\"}";
    }
}
