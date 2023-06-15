package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;

public interface DepartmentService {
    DepartmentDto getDepartment(String id);
    DepartmentResponse registerDepartment(DepartmentDto departmentDto) throws DuplicateDepartmentIdException;
    void updateDepartment(String data, String id);
    void deleteDepartment(String id);
}
