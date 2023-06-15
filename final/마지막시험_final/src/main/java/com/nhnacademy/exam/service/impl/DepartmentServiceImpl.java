package com.nhnacademy.exam.service.impl;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto getDepartment(String id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
        return new DepartmentDto(department.getId(),department.getName());
    }

    @Override
    public DepartmentResponse registerDepartment(DepartmentDto departmentDto) throws DuplicateDepartmentIdException {
        try{
            if(Objects.nonNull(departmentRepository.findById(departmentDto.getId()).orElseGet(()->null))){
                throw new DuplicateDepartmentIdException(departmentDto.getId());
            }
        } catch (NullPointerException e){

        }
        Department department = departmentRepository.save(new Department(departmentDto.getId(),departmentDto.getName()));
        return new DepartmentResponse(department.getId());
    }

    @Override
    public void updateDepartment(String data, String id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
        departmentRepository.save(new Department(department.getId(),data));
    }

    @Override
    public void deleteDepartment(String id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
        departmentRepository.deleteById(department.getId());

    }
}
