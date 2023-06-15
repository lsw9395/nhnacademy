package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;

import java.util.List;

public interface WaterUsageBillService {
    List<WaterBill> calculateBill(int usage);
}
