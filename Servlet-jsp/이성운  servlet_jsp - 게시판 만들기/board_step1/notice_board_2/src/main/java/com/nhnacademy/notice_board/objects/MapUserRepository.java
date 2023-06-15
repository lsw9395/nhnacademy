package com.nhnacademy.notice_board.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapUserRepository implements UserRepository{
    private Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public void modify(User user) {
        if(userMap.containsKey(user.getId())){
            userMap.replace(user.getId(), user);
        } else{
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    @Override
    public void remove(String id) {
        if(userMap.containsKey(id)){
            userMap.remove(id);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    @Override
    public User getUser(String id) {
        if(userMap.containsKey(id)){
            return userMap.get(id);
        }
        throw new RuntimeException("존재하지 않는 유저입니다.");
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>(userMap.values());
        return users;
    }
}
