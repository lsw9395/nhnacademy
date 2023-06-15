package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.Department;
import org.junit.jupiter.api.BeforeEach;
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
    private TestEntityManager testEntityManager;
    @Autowired
    private DepartmentRepository departmentRepository;


    private Department department1;
    private Department department2;
    private Department department3;

    @BeforeEach
    void init(){
        department1 = new Department("Test001","테스트1팀");
        department2 = new Department("Test002","테스트2팀");
        department3 = new Department("Test003","테스트3팀");
        testEntityManager.persistAndFlush(department1);
        testEntityManager.persistAndFlush(department2);
        testEntityManager.persistAndFlush(department3);
    }

    @Test
    @DisplayName("조회")
    void testFindId(){
        Department department = departmentRepository.findById("Test001").orElseThrow();
        assertThat(department).isNotNull();
    }
    @Test
    @DisplayName("등록")
    void testSave(){
        Department department = new Department("CS0001","CS팀");
        testEntityManager.persist(department);
        departmentRepository.save(department);
        Department department2 = departmentRepository.findById("CS0001").orElseThrow();
        assertThat(department2.getId()).isEqualTo(department.getId());
    }
    @Test
    @DisplayName("삭제")
    void testDelete(){
        departmentRepository.deleteById("Test001");
        Department department = departmentRepository.findById("CS002").orElseGet(()->null);
        assertThat(department).isNull();
    }
}