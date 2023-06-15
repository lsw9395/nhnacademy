package com.nhnacademy.notice_board.objects;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MapPostRepository implements PostRepository{
    AtomicInteger index = new AtomicInteger(0);
    Map<Long, Post> postMap = new HashMap<>();
    @Override
    public long register(Post post) {
        if(Objects.isNull(post.getWriteTime())){
            post.setWriteTime(LocalDateTime.now());
        }

        postMap.put((long)index.incrementAndGet(),post);
        return index.get();
    }

    @Override
    public void modify(Post post) {
        postMap.replace(post.getId(),post);
    }

    @Override
    public void remove(long id) {
        postMap.remove(id);
    }

    @Override
    public Post getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getPosts() {
        List<Post> postList = new ArrayList<>(postMap.values());
        return postList;
    }
}
