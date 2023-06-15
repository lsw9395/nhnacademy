package com.nhnacademy.springmvc.postlist.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class User implements Serializable {
    public User(){

    }
    public User(String id,String password,String name,String profileFileName){
        this.id=id;
        this.password=password;
        this.name=name;
        this.profileFileName=profileFileName;
    }
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String profileFileName;


}
