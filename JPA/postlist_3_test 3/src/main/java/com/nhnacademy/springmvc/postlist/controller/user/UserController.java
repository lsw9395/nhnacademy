package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.UserDto;
import com.nhnacademy.springmvc.postlist.domain.UserDto2;
import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import com.nhnacademy.springmvc.postlist.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JpaUserRepository userRepository;
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") String id){
        return userRepository.readById(id);
    }
    @GetMapping("/users")
    public List<UserDto2> getUsers(Pageable pageable){
        return userRepository.readAllBy(pageable).getContent();
    }
}
