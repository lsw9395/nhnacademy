package com.nhnacademy.springboot.postlist.board.controller.user;


import com.nhnacademy.springboot.postlist.board.domain.UserDto;
import com.nhnacademy.springboot.postlist.board.domain.UserDto2;
import com.nhnacademy.springboot.postlist.board.repository.JpaUserRepository;
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
