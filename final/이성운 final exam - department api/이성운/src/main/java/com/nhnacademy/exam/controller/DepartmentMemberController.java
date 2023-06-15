package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.exception.NotSupportedContentTypeException;
import com.nhnacademy.exam.service.DepartmentMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DepartmentMemberController {
    private final DepartmentMemberService departmentMemberService;

    @GetMapping(value = "/department-members")
    public ResponseEntity<Object> getDepartmentMembers(
            @RequestHeader(value = "Accept") String accept,
            @RequestParam("departmentIds") String departmentIds) throws NotSupportedContentTypeException, MissingServletRequestParameterException {
        log.info(accept);
        if(!(Objects.equals(accept,"application/json")||Objects.equals(accept,"application/xml"))){
            throw new NotSupportedContentTypeException();
        }
        if(departmentIds==null || departmentIds.isEmpty()){
            throw new MissingServletRequestParameterException("departmentIds", "String");
        }
        return ResponseEntity
                .ok(departmentMemberService.getDepartmentMemberWithIds(departmentIds));
    }
}
