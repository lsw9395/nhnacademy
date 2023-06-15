package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.exception.NotFoundPostId;
import com.nhnacademy.springmvc.postlist.service.PostService;
import com.nhnacademy.springmvc.postlist.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Controller
@Slf4j
@RequiredArgsConstructor
public class PostViewController {
    private final PostService postService;

    @GetMapping("/post_view")
    public String view(@RequestParam("id") Long id, Model model, HttpServletRequest req) throws NotFoundPostId {
        Post post = postService.getPost(id);
        if(Objects.isNull(post)){
            throw new NotFoundPostId(id);
        }
        Cookie cookie = CookieUtils.getCookie(req,"check_counter");
        if(!(cookie.getValue().contains("/"+id.toString()+"/"))){
            post.setViewCount(post.getViewCount()+1);
        }
        model.addAttribute("post",post);
        return "post/postview";
    }
}
