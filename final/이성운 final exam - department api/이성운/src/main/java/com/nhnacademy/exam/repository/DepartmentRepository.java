package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
