package com.nhnacademy.springmvc.domain;


import com.nhnacademy.springmvc.annotation.EnumPattern;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StudentRegisterRequest {
    @Size(min = 5 ,max = 20)
    private String id;
    @NotBlank
    private String name;
    @EnumPattern(regexp = "M|F" ,message="M이나 F중에서 골라주세요.")
    private Gender gender;
    @Min(20)
    @Max(30)
    private int age;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

}
