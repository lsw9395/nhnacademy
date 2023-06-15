package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.domain.DepartmentUpdateDto;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @Test
    void getDepartment() {
        DepartmentDto result = departmentService.getDepartment("CS004");
        assertThat(result).isNotNull();
    }

    @Test
    void registerDepartment() throws DuplicateDepartmentIdException {
        DepartmentDto register = new DepartmentDto("CS0001","test");
        DepartmentResponse response = departmentService.registerDepartment(register);
        DepartmentDto result = departmentService.getDepartment("CS0001");
        assertThat(register.getId()).isEqualTo(result.getId());
        assertThat(response.getId()).isEqualTo(register.getId());
    }

    @Test
    void updateDepartment() {
        DepartmentUpdateDto updateDto = new DepartmentUpdateDto("test22");
        departmentService.updateDepartment(updateDto.getName(),"CS004");
        DepartmentDto result = departmentService.getDepartment("CS004");
        assertThat(result.getName()).isEqualTo(updateDto.getName());
    }

    @Test
    void deleteDepartment() {
        departmentService.deleteDepartment("CS003");
        DepartmentDto result;
        try {
             result= departmentService.getDepartment("CS003");
        } catch (Exception e){
            result = null;
        }

        assertThat(result).isNull();
    }
}