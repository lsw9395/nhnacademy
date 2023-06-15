package com.nhnacademy.edu.springframework.project;


import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.report.ResultReport;
import com.nhnacademy.edu.springframework.project.repository.BillRepository;
import com.nhnacademy.edu.springframework.project.service.WaterUsageBillService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        String filePath = "./src/main/resources/data/Tariff_20220331.csv";
        String type = filePath.substring(filePath.lastIndexOf(".")+1);
        if(type.equals("json")){
            context.getEnvironment().setActiveProfiles(type);
        } else {
            context.getEnvironment().setActiveProfiles(type);
        }
        context.register(MainConfig.class);
        context.refresh();
        BillRepository billRepository = context.getBean("defaultBillRepository", BillRepository.class);
        billRepository.fileLoad(filePath);
        WaterUsageBillService service = context.getBean("defaultWaterUsageBillService", WaterUsageBillService.class);
        ResultReport report = context.getBean("defaultResultReport",ResultReport.class);
        while(true){
            try{
                report.report(service.calculateBill(sc.nextInt()));
            }catch (Exception e){
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}