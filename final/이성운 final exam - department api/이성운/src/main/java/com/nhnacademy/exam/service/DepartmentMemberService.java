package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentMemberDto;

import java.util.List;

public interface DepartmentMemberService {
    List<DepartmentMemberDto> getDepartmentMemberWithIds(String departmentIds);
}