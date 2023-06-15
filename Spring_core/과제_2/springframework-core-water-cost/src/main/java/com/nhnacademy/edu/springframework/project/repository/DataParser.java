package com.nhnacademy.edu.springframework.project.repository;

import java.util.List;

public interface DataParser {
    List<WaterBill> parse(String filepath);
}
