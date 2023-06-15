package com.nhnacademy.springboot.postlist.board.controller.user;


import com.nhnacademy.springboot.postlist.board.domain.UserDto;
import com.nhnacademy.springboot.postlist.board.exception.NotFoundUser;
import com.nhnacademy.springboot.postlist.board.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserListController {
    private final JpaUserRepository userService;
    @GetMapping("/user_list")
    public String view(Pageable pageable, Model model, HttpServletRequest req) throws NotFoundUser {
        Page<UserDto> page = userService.getAllBy(pageable);
        List<UserDto> userEntityList = page.getContent();
        int pageN = page.getNumber();
        int startPage = ((pageN-1)/5) *5 +1;
        int endPage =  Math.min(startPage+4,page.getTotalPages());
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("datas",userEntityList);
        model.addAttribute("pages",page);
        model.addAttribute("visitorCounter",req.getServletContext().getAttribute("visitorCounter"));
        return "user/userlist";
    }
}
