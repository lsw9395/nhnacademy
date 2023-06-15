package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.PostDto;
import com.nhnacademy.springmvc.postlist.domain.PostDto2;
import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.repository.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final JpaPostRepository jpaPostRepository;

    @GetMapping("/posts/{id}")
    public List<PostDto> getPostsByUserId(@PathVariable("id") String id){
        return jpaPostRepository.findByUser_id(id);
    }
    @GetMapping("/posts")
    public List<PostDto2> getPostsByPage(Pageable pageable){
        return jpaPostRepository.readAllBy(pageable).getContent();
    }
    @GetMapping("/posts/viewCount/{viewCount}")
    public List<PostDto2> getPostsByPage(@PathVariable("viewCount") int viewCount){
        log.error("{}",viewCount);
        return jpaPostRepository.getPostsHavingViewCountAtLeast(viewCount);
    }

}
