package com.nhnacademy.notice_board.objects;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    void remove(String id);

    User getUser(String id);
    List<User> getUsers();
}
