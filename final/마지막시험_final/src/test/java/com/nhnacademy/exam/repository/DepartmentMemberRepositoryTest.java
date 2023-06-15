package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentMemberRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DepartmentMemberRepository repository;

    private DepartmentMember departmentMember1;
    private DepartmentMember departmentMember2;
    private DepartmentMember departmentMember3;
    @BeforeEach
    void init(){
        Department department1 = new Department("1","dep 1");
        departmentMember1 = new DepartmentMember(new DepartmentMember.Pk("1","1"),department1,new Employee("1","emp 1"));
        departmentMember2 = new DepartmentMember(new DepartmentMember.Pk("1","2"),department1,new Employee("2","emp 2"));
        departmentMember3 = new DepartmentMember(new DepartmentMember.Pk("1","3"),department1,new Employee("3","emp 3"));

        testEntityManager.persistAndFlush(departmentMember1);
        testEntityManager.persistAndFlush(departmentMember2);
        testEntityManager.persistAndFlush(departmentMember3);
    }
    @Test
    void testFindId(){
        String[] data = {"1"};
        List<DepartmentMemberDto> result = repository.getByDepartmentId(data);
        assertThat(result).isNotEmpty();
    }
}