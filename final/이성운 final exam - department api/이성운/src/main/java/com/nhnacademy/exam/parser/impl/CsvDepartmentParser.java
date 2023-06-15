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
public class CsvDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "csv";
    }

    @Override
    public List<EmployeeParseDto> parsing(File file) {
        List<EmployeeParseDto> result = new ArrayList<>();
        try(BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(new FileInputStream(file)));){
            String line = null;
            while ((line=bufferedReader.readLine())!=null){
                String[] lineContents = line.split(",",-1);
                if(lineContents!=null&&lineContents[0].contains("20")){
                    result.add(new EmployeeParseDto(lineContents[0].trim(),lineContents[1].trim(),lineContents[2].trim(),lineContents[3].trim()));
                }

            }
            for(EmployeeParseDto e:result){
                log.info(e.getName());
            }
        } catch (Exception e){
        }
        return result;
    }
}
