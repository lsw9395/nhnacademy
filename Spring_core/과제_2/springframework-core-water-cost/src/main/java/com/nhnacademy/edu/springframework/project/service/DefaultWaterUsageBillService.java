package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import com.nhnacademy.edu.springframework.project.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultWaterUsageBillService implements WaterUsageBillService {
    private List<WaterBill> topFive;
    private final BillRepository billRepository;
    @Autowired
    public DefaultWaterUsageBillService(@Qualifier("defaultBillRepository") BillRepository billRepository){
        this.billRepository = billRepository;
    }
    @Override
    public List<WaterBill> calculateBill(int usage) {
        topFive = billRepository.searchBill(usage);
        return topFive;
    }
}
