package com.nhnacademy.springmvc.postlist.domain;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @NotBlank(message = "ID is empty")
    @Size(min = 5,message = "아이디가 5글자 이상이어야합니다.")
    @Size(max = 20, message = "아이디가 20글자 이하이어야합니다.")
    private String id;
    @NotBlank(message = "Pwd is empty")
    @Size(min = 5,message = "비밀번호가 5글자 이상이어야합니다.")
    @Size(max = 20, message = "비밀번호가 15글자 이하이어야합니다.")
    private String password;
    @NotBlank(message = "name is empty")
    private String name;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
