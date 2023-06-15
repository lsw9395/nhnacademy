package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.domain.QDepartmentMemberDto;
import com.nhnacademy.exam.entity.DepartmentMember;
import com.nhnacademy.exam.entity.QDepartmentMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomDepartmentMemberRepositoryImpl extends QuerydslRepositorySupport implements CustomDepartmentMemberRepository{

    @PersistenceContext
    private EntityManager entityManager;
    public CustomDepartmentMemberRepositoryImpl() {
        super(DepartmentMember.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentMemberDto> getByDepartmentId(String[] departmentId) {
        QDepartmentMember departmentMember = QDepartmentMember.departmentMember;

        return from(departmentMember)
                .select(new QDepartmentMemberDto(departmentMember.department,departmentMember.employee))
                .where(departmentMember.pk.departmentId.in(departmentId))
                .orderBy(departmentMember.pk.departmentId.asc(), departmentMember.pk.memberId.asc())
                .fetch();
    }
    @Transactional
    @Override
    public void setForeignKeyChecks(int option){
        Query query = entityManager.createNativeQuery("SET FOREIGH_KEY_CHECKS = :option");
        query.setParameter("option",option);
        query.executeUpdate();
    }
    @Transactional
    @Override
    public void truncate(){
        Query query = entityManager.createNativeQuery("TRUNCATE TABLE department_members");
        query.executeUpdate();
        Query query2 = entityManager.createNativeQuery("TRUNCATE TABLE employees");
        query2.executeUpdate();
        Query query3 = entityManager.createNativeQuery("TRUNCATE TABLE departments");
        query3.executeUpdate();
    }
}
