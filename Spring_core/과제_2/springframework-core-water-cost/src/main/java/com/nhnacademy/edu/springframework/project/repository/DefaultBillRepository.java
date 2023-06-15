package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultBillRepository implements BillRepository {
    private static List<WaterBill> waterBillList;
    private final DataParser parser;
    @Autowired(required = false)
    public DefaultBillRepository(DataParser parser){
        this.parser =parser;
    }
    @Override
    public void fileLoad(String filepath) {
        waterBillList = parser.parse(filepath);
    }
    @Override
    public List<WaterBill> findAll(){
        return waterBillList;
    }
    @Override
    public List<WaterBill> searchBill(int usage) {
        for(WaterBill waterBill:waterBillList){
            waterBill.setBillTotal(usage);
        }
        List<WaterBill> topFive=waterBillList.stream()
                .sorted(Comparator.comparing(waterBill -> waterBill.getBillTotal()))
                .limit(5).collect(Collectors.toList());
        return topFive;
    }
}
