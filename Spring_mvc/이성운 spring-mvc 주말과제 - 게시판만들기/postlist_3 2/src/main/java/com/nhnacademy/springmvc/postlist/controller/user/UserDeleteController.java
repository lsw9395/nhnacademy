package com.nhnacademy.springmvc.postlist.controller.user;


import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.exception.NotFoundUserId;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserDeleteController {
    private final UserService userService;
    @GetMapping("/user_delete")
    public String delete(@RequestParam("id") String id) throws NotFoundUserId {
        User user = userService.getUser(id);
        if(Objects.isNull(user)){
            throw new NotFoundUserId(id);
        }
        userService.remove(id);
        return "redirect:/user_list?page=1";
    }

}
