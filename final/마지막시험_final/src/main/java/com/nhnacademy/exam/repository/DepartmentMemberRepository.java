package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMemberRepository extends JpaRepository<DepartmentMember,DepartmentMember.Pk>, CustomDepartmentMemberRepository {
}
