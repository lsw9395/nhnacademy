package com.nhnacademy.exam.domain;

import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.Employee;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentMemberDto {
    private Department department;
    private Employee employee;

    @QueryProjection
    public DepartmentMemberDto(Department department, Employee employee){
        this.department =department;
        this.employee =employee;
    }
}
