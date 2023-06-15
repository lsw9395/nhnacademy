package com.nhnacademy.exam.service.impl;

import com.nhnacademy.exam.domain.DepartmentMemberDto;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.service.DepartmentMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentMemberServiceImpl implements DepartmentMemberService {
    private final DepartmentMemberRepository departmentMemberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentMemberDto> getDepartmentMemberWithIds(String departmentIds) {
        String[] departmentIdList = departmentIds.replace(" ", "")
                .split(",|\\.|/|-|\\+");

        return departmentMemberRepository.getByDepartmentId(departmentIdList);
    }
}
