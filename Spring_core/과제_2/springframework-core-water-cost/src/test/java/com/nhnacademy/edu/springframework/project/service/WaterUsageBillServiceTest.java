package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.BillRepository;
import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class WaterUsageBillServiceTest {
    String filePath = "./src/test/resources/data/Tariff_test.csv";
    BillRepository repository;
    WaterUsageBillService service;
    @BeforeEach
    void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        repository = context.getBean("defaultBillRepository", BillRepository.class);
        service = context.getBean("defaultWaterUsageBillService", WaterUsageBillService.class);
    }
    @Test
    void calculateBill() {
        WaterBill wb1 = new WaterBill("동두천시","가정용",690);wb1.setBillTotal(1000);
        WaterBill wb2 = new WaterBill("동두천시","가정용",1090);wb2.setBillTotal(1000);
        WaterBill wb5 = new WaterBill("동두천시","가정용",1530);wb5.setBillTotal(1000);
        WaterBill wb3 = new WaterBill("동두천시","일반용",1410);wb3.setBillTotal(1000);
        WaterBill wb4 = new WaterBill("동두천시","일반용",1480);wb4.setBillTotal(1000);
        List<WaterBill> expect = Arrays.asList(wb1,wb2,wb3,wb4,wb5);
        repository.fileLoad(filePath);
        List<WaterBill> actual = service.calculateBill(1000);
        Assertions.assertEquals(expect.size(),actual.size());
        for(int i =0 ; i < expect.size();i++){
            Assertions.assertTrue(expect.get(i).getCity().equals(actual.get(i).getCity()));
            Assertions.assertTrue(expect.get(i).getSector().equals(actual.get(i).getSector()));
            Assertions.assertTrue(expect.get(i).getUnitPrice()==actual.get(i).getUnitPrice());
            Assertions.assertTrue(expect.get(i).getBillTotal()==actual.get(i).getBillTotal());
        }
    }
}
