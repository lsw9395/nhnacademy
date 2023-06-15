package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.domain.DepartmentUpdateDto;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService departmentService;
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentServiceImpl(departmentRepository);
    }
    @Test
    void getDepartment() {
        given(departmentRepository.findById(any())).willReturn(Optional.of(new Department("test1", "테스트1")));
        DepartmentDto result = departmentService.getDepartment("test1");
        assertThat(result).isNotNull();
    }

    @Test
    void registerDepartment() throws DuplicateDepartmentIdException {
        DepartmentDto register = new DepartmentDto("test1","테스트1");
        Department department = new Department("test1", "테스트1");
        given(departmentRepository.save(any(Department.class))).willReturn(department);
        given(departmentRepository.findById(anyString())).willReturn(Optional.empty());
        DepartmentResponse response = departmentService.registerDepartment(register);
        assertThat(response.getId()).isEqualTo(register.getId());
    }

    @Test
    void updateDepartment() {
        DepartmentUpdateDto updateDto = new DepartmentUpdateDto("테스트1");
        given(departmentRepository.findById(any())).willReturn(Optional.of(new Department("test1", "테스트1")));
        given(departmentRepository.save(any(Department.class))).willReturn(new Department("test1", "테스트1"));
        departmentService.updateDepartment(updateDto.getName(),"test1");
        DepartmentDto result = departmentService.getDepartment("test1");
        assertThat(result.getName()).isEqualTo(updateDto.getName());
    }

    @Test
    void deleteDepartment() throws DuplicateDepartmentIdException {
        doNothing().when(departmentRepository).deleteById("test1");
        given(departmentRepository.findById(any())).willReturn(Optional.of(new Department("test1", "테스트1")));
        departmentService.deleteDepartment("test1");


        verify(departmentRepository).deleteById("test1");
    }
}