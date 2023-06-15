package com.nhnacademy.edu.springframework.project.repository;

import java.util.List;

public interface BillRepository {
    void fileLoad(String filepath);
    List<WaterBill> searchBill(int usage);

    List<WaterBill> findAll();
}
