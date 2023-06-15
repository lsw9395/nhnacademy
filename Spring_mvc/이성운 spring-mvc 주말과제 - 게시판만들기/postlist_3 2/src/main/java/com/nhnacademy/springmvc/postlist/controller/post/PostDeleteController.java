package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.exception.NotFoundPostId;
import com.nhnacademy.springmvc.postlist.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping()
public class PostDeleteController {
    private final PostService postService;

    @GetMapping("/post_delete")
    public String delete(@RequestParam("id") Long id) throws NotFoundPostId {
        Post post = postService.getPost(id);
        if(Objects.isNull(post)){
            throw new NotFoundPostId(id);
        }
        postService.remove(id);
        return "redirect:/post_list?page=1";
    }
}
