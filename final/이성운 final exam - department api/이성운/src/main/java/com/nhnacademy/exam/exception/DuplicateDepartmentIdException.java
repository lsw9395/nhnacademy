package com.nhnacademy.exam.exception;

public class DuplicateDepartmentIdException extends Exception{
    private String departmentId;

    public DuplicateDepartmentIdException(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
