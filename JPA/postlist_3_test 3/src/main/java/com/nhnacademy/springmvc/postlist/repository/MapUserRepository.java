package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapUserRepository implements UserRepository {
    private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, User> getUserMap() {
        return userMap;
    }

    @Override
    public void setUserMap() {

    }

}

