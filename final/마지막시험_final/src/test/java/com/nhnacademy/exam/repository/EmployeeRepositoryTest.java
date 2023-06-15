package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    @BeforeEach
    void init(){
        employee1 = new Employee("Test001","테스트1");
        employee2 = new Employee("Test002","테스트2");
        employee3 = new Employee("Test003","테스트3");
        testEntityManager.persistAndFlush(employee1);
        testEntityManager.persistAndFlush(employee2);
        testEntityManager.persistAndFlush(employee3);
    }
    @Test
    @DisplayName("조회")
    void testFindId(){
        Employee employee = employeeRepository.findById("Test001").orElseThrow();
        assertThat(employee).isNotNull();
    }
    @Test
    @DisplayName("등록")
    void testSave(){
        Employee employee = new Employee("Test004","테스트4");
        testEntityManager.persist(employee);
        employeeRepository.save(employee);
        Employee employee5 = employeeRepository.findById("Test004").orElseThrow();
        assertThat(employee5.getId()).isEqualTo(employee.getId());
    }
    @Test
    @DisplayName("삭제")
    void testDelete(){
        employeeRepository.deleteById("Test001");
        Employee employee = employeeRepository.findById("CS002").orElseGet(()->null);
        assertThat(employee).isNull();
    }
}