package com.nhnacademy.exam.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.Employee;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.EmployeeParseDto;
import com.nhnacademy.exam.parser.impl.CsvDepartmentParser;
import com.nhnacademy.exam.parser.impl.JsonDepartmentParser;
import com.nhnacademy.exam.parser.impl.TextDepartmentParser;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.repository.EmployeeRepository;
import com.nhnacademy.exam.service.DBSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DBSettingServiceImpl implements DBSettingService {
    private final DepartmentMemberRepository departmentMemberRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private DepartmentParserResolver resolver;
    @Override
    public void dbSetting(String fileName) throws IOException {
        //initializeDb();
        resolver = new DepartmentParserResolver(
                List.of(new TextDepartmentParser(), new JsonDepartmentParser(new ObjectMapper()), new CsvDepartmentParser()));
        Resource resource = (Resource) new PathMatchingResourcePatternResolver().getResource("classpath:" + "data/"+fileName);
        List<EmployeeParseDto> parseList = resolver
                .getDepartmentParser(fileName)
                .parsing(resource.getFile());
        for(EmployeeParseDto e :parseList){
            Employee employee = employeeRepository.findById(e.getId()).
                    orElseGet(()->employeeRepository.save(new Employee(e.getId(),e.getName())));
            Department department = departmentRepository.findById(e.getDepartmentCode()).
                    orElseGet(()->departmentRepository.save(new Department(e.getDepartmentCode(),e.getDepartment())));
            DepartmentMember departmentMember = new DepartmentMember(
                    new DepartmentMember.Pk(e.getId(),e.getDepartmentCode()),
                    department,
                    employee
            );
            departmentMemberRepository.save(departmentMember);
        }
    }

    private void initializeDb(){
        departmentMemberRepository.setForeignKeyChecks(0);
        departmentMemberRepository.truncate();
        departmentMemberRepository.setForeignKeyChecks(1);
    }
}
