package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DefaultResultReport implements ResultReport{

    @Override
    public void report(List<WaterBill> topFive) {
        for(WaterBill waterBill: topFive){
            System.out.println(waterBill);
        }
    }
}
