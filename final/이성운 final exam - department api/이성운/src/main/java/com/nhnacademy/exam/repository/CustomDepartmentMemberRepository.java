package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.DepartmentMember;

import java.util.List;

public interface CustomDepartmentMemberRepository {
    List<DepartmentMemberDto> getByDepartmentId(String[] departmentId);
    public void setForeignKeyChecks(int option);
    public void truncate();
}
