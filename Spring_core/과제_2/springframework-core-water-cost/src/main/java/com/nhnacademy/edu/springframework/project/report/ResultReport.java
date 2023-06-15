package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;

import java.util.List;

public interface ResultReport {
    void report(List<WaterBill> topFive);
}
