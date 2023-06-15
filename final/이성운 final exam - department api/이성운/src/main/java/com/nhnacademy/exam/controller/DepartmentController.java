package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.domain.DepartmentDto;
import com.nhnacademy.exam.domain.DepartmentResponse;
import com.nhnacademy.exam.domain.DepartmentUpdateDto;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping(value = "/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String id) {
        DepartmentDto department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/departments")
    public DepartmentResponse registerDepartment(@RequestBody DepartmentDto departmentDto) throws DuplicateDepartmentIdException {
        return departmentService.registerDepartment(departmentDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/departments/{id}")
    public void updateDepartment(@RequestBody DepartmentUpdateDto updateDto, @PathVariable String id){
        departmentService.updateDepartment(updateDto.getName(),id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable String id){
        departmentService.deleteDepartment(id);
    }
}
