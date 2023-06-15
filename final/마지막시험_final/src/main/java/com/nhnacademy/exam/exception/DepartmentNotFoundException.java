package com.nhnacademy.exam.exception;

public class DepartmentNotFoundException extends RuntimeException{
    private String departmentId;

    public DepartmentNotFoundException(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
