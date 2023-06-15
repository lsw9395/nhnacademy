package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.Post;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public interface PostRepository {
    public AtomicLong getIndex() ;

    public void setIndex(AtomicLong index) ;

    public Map<Long, Post> getPostMap() ;

    public void setPostMap();
}
