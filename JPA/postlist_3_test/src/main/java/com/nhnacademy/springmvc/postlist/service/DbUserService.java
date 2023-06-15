package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUserService {
    private final UserMapper userMapper;
    public void add(User user){
        userMapper.save(user);
    }

    public void modify(User user){
        userMapper.update(user);
    }

    public void remove(String id){
        userMapper.remove(id);
    }

    public User getUser(String id){

        return userMapper.getUser(id);
    }
    public List<User> getUsers(){
        return userMapper.getUsers();
    }

    public long getTotalCount(){
        return userMapper.getTotalCount();
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
    public int getPageNum(long totalSize, int size){
        int totalPage=0;
        totalPage = (int) (( (totalSize - 1) / size ) + 1);
        return totalPage;
    }
    public List<User> paging(int page, int size){
        List<User> users = getUsers();
        List<User> pageUsers = new ArrayList<>();

        int start = (page-1)*size+1;
        long end = page*size;
        if(end>getTotalCount()){
            end = getTotalCount();
        }
        for(int i=start-1;i<end;i++){
            pageUsers.add(users.get(i));
        }
        return pageUsers;
    }
}
