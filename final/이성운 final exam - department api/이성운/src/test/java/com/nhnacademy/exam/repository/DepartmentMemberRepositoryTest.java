package com.nhnacademy.exam.repository;

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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentMemberRepositoryTest {
    @Autowired
    private DepartmentMemberRepository repository;

    @Test
    void testFindId(){
        String[] data = {"CS005"};
        List<DepartmentMemberDto> result = repository.getByDepartmentId(data);
        assertThat(result).isNotEmpty();
    }
}