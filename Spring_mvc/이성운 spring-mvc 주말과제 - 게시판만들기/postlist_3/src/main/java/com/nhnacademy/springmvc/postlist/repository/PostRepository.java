package com.nhnacademy.springmvc.postlist.repository;


import com.nhnacademy.springmvc.postlist.domain.Post;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface PostRepository {
    public AtomicInteger getIndex() ;

    public void setIndex(AtomicInteger index) ;

    public Map<Long, Post> getPostMap() ;

    public void setPostMap(Map<Long, Post> postMap) ;
}
