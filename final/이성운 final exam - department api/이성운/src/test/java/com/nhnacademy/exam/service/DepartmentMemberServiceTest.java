package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DepartmentMemberServiceTest {

    @Autowired
    DepartmentMemberService departmentMemberService;
    @Test
    void getDepartmentMemberWithIds() {
        List<DepartmentMemberDto> result = departmentMemberService.getDepartmentMemberWithIds("CS005");
        assertThat(result).isNotEmpty();
    }
}