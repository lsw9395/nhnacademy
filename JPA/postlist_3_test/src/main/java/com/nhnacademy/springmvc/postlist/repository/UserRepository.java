package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.User;


import java.util.Map;

public interface UserRepository {
    public Map<String, User> getUserMap() ;
    public void setUserMap();
}
