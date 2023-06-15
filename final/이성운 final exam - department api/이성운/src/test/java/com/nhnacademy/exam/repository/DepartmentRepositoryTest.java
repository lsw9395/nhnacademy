package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.lang.annotation.Documented;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("조회")
    void testFindId(){
        Department department = departmentRepository.findById("CS005").orElseThrow();
        assertThat(department).isNotNull();
    }
    @Test
    @DisplayName("등록")
    void testSave(){
        Department department = new Department("CS0001","CS팀");
        departmentRepository.save(department);
        Department department2 = departmentRepository.findById("CS0001").orElseThrow();
        assertThat(department2.getId()).isEqualTo(department.getId());
    }
    @Test
    @DisplayName("삭제")
    void testDelete(){
        departmentRepository.deleteById("CS002");
        Department department = departmentRepository.findById("CS002").orElseGet(()->null);
        assertThat(department).isNull();
    }
}