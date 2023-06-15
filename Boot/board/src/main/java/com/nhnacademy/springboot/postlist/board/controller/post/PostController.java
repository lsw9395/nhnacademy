package com.nhnacademy.springboot.postlist.board.controller.post;

import com.nhnacademy.springboot.postlist.board.domain.PostDto;
import com.nhnacademy.springboot.postlist.board.domain.PostDto2;
import com.nhnacademy.springboot.postlist.board.repository.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
