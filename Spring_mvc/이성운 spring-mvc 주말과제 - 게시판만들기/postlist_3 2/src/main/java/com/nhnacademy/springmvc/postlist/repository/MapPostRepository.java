package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.Post;
import org.springframework.stereotype.Repository;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MapPostRepository implements PostRepository {
    AtomicLong index = new AtomicLong(0);
    Map<Long, Post> postMap = new HashMap<>();

    @Override
    public AtomicLong getIndex() {
        return index;
    }

    @Override
    public void setIndex(AtomicLong index) {
        this.index = index;
    }

    @Override
    public Map<Long, Post> getPostMap() {
        return postMap;
    }

    @Override
    public void setPostMap() {

    }


}
