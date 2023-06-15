package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.Post;
import org.springframework.stereotype.Repository;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MapPostRepository implements PostRepository {
    AtomicInteger index = new AtomicInteger(0);
    Map<Long, Post> postMap = new HashMap<>();

    @Override
    public AtomicInteger getIndex() {
        return index;
    }

    @Override
    public void setIndex(AtomicInteger index) {
        this.index = index;
    }

    @Override
    public Map<Long, Post> getPostMap() {
        return postMap;
    }

    @Override
    public void setPostMap(Map<Long, Post> postMap) {
        this.postMap = postMap;
    }
}
