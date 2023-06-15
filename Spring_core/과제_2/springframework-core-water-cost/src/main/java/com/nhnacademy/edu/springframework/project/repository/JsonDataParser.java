package com.nhnacademy.edu.springframework.project.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Profile("json")
public class JsonDataParser implements DataParser{
    @Override
    public List<WaterBill> parse(String filepath) {
        System.out.println("Json_File");
        List<WaterBill> waterBillList = new ArrayList();
        File jsonFile = new File(filepath);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> data;
        try {
            data = mapper.readValue(jsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map<String, Object> row : data) {
            String city = (String) row.get("지자체명");
            String sector = (String) row.get("업종");
            int unitPrice = (int) row.get("구간금액(원)");
            WaterBill waterBill = new WaterBill(city, sector, unitPrice);
            waterBillList.add(waterBill);
        }
        return waterBillList;
    }
}
