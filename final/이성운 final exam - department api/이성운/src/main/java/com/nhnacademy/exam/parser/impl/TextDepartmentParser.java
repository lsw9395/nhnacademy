package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.EmployeeParseDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<EmployeeParseDto> parsing(File file) throws IOException {
        List<EmployeeParseDto> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            boolean isFristLine = true;
            while((line = reader.readLine())!=null){
                if(isFristLine){
                    isFristLine = false;
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("잘못된 데이터 형식입니다: " + line);
                }
                if(parts[1].contains("20"))
                result.add(new EmployeeParseDto(parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim()));
            }
        }
        for(EmployeeParseDto e: result){
            log.info(e.getName());
        }
        return result;
    }
}
