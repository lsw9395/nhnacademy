package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(@Qualifier("jsonUserRepository") UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void add(User user) {
        userRepository.getUserMap().put(user.getId(),user);
        userRepository.setUserMap();
    }


    public void modify(User user) {
        userRepository.getUserMap().replace(user.getId(), user);
        userRepository.setUserMap();
    }


    public void remove(String id) {
        userRepository.getUserMap().remove(id);
        userRepository.setUserMap();
    }


    public User getUser(String id) {
        return userRepository.getUserMap().get(id);

    }


    public List<User> getUsers() {
        return new ArrayList<>(userRepository.getUserMap().values());
    }

    public int getTotalCount(){
        List<User> users = getUsers();
        return users.size();
    }
    public Page<User> getPagedPosts(int page, int size){
        Page<User> page2 = new Page();
        page2.setPageNumber(page);
        page2.setPageSize(size);
        page2.setTotalPageCount(getPageNum(getTotalCount(),size));
        page2.setTotalCount(getTotalCount());
        page2.setList(paging(page,size));
        return page2;
    }
    public int getPageNum(int totalSize, int size){
        int totalPage=0;
        totalPage =  ( (totalSize - 1) / size ) + 1;
        return totalPage;
    }
    public List<User> paging(int page, int size){
        List<User> users = getUsers();
        List<User> pageUsers = new ArrayList<>();

        int start = (page-1)*size+1;
        int end = page*size;
        if(end>getTotalCount()){
            end = getTotalCount();
        }
        for(int i=start-1;i<end;i++){
            pageUsers.add(users.get(i));
        }
        return pageUsers;
    }
}
