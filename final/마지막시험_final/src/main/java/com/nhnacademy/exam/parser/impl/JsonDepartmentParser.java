package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.EmployeeParseDto;
import com.nhnacademy.exam.parser.DepartmentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

    private final ObjectMapper objectMapper;
    @Override
    public String getFileType() {
        return "json";
    }

    @Override
    public List<EmployeeParseDto> parsing(File file) throws IOException {
        String json = new String(Files.readAllBytes(file.toPath()));
        log.info(json);
        List<EmployeeParseDto> result = objectMapper.readValue(json,objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeeParseDto.class));
        for(EmployeeParseDto e: result){
            log.info(e.getName());
        }
        return result;
    }
}
