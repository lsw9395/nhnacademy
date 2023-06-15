package com.nhnacademy.springmvc.postlist.mapper;

import com.nhnacademy.springmvc.postlist.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    void update(Post post);
    void remove(long id);
    Post getPost(long id);
    List<Post> getPosts();
    long getTotalCount();
}
