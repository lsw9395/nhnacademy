package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.domain.PostRegisterRequest;
import com.nhnacademy.springmvc.postlist.exception.NotFoundPostId;
import com.nhnacademy.springmvc.postlist.service.DbPostService;
import com.nhnacademy.springmvc.postlist.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostUpdateController {
    private final DbPostService postService;
    @GetMapping("/post_update")
    public String update(@RequestParam(value = "id")Long id, Model model) throws NotFoundPostId {
        Post post = postService.getPost(id);
        if(Objects.isNull(post)){
            throw new NotFoundPostId(id);
        }
        model.addAttribute("post",post);
        model.addAttribute("postRegisterRequest",new PostRegisterRequest());
        return "post/postregister";
    }
    @PostMapping("/post_update")
    public String getPost(@Validated @ModelAttribute PostRegisterRequest postRegisterRequest, BindingResult bindingResult
            , Model model,@RequestParam("id") Long id) throws NotFoundPostId {
        Post post = postService.getPost(id);
        if(Objects.isNull(post)){
            throw new NotFoundPostId(id);
        }
        if(bindingResult.hasErrors()){
            log.info("binding error");
            model.addAttribute("post",post);
            return "post/postregister";
        }
        Post newPost = new Post(post.getId(),postRegisterRequest.getTitle(),postRegisterRequest.getContent(),post.getWriterUserId(),post.getWriteTime(),post.getViewCount());
        postService.update(newPost);
        return "redirect:/post_list?page=1";
    }
}
