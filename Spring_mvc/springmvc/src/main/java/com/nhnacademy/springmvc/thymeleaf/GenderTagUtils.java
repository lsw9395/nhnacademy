package com.nhnacademy.springmvc.thymeleaf;

import com.nhnacademy.springmvc.domain.Gender;

public class GenderTagUtils {
    public String gender(Gender gender){
        if(gender.name().equals("M")){
            return "남성";
        } else if (gender.name().equals("F")) {
            return "여성";
        }else {
            return "";
        }
    }
}
