package com.nhnacademy.edu.springframework.project.repository;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile({"csv","default"})
public class CsvDataParser implements DataParser {
    private List<WaterBill> waterBillList;
    @Override
    public List<WaterBill> parse(String filepath){
        System.out.println("CSV_File");
        File csvFile = new File(filepath);
        waterBillList = new ArrayList<>();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

        List<List<String>> lists = null;
        try {
            MappingIterator<List<String>> rows = csvMapper.readerFor(List.class).readValues(csvFile);
            lists = rows.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lists.remove(0);
        for(List<String> list :lists){
            WaterBill waterBill = new WaterBill(list.get(1).trim(),list.get(2).trim(),Integer.parseInt(list.get(6)));
            waterBillList.add(waterBill);
        }
        return waterBillList;
    }
}
