package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepository postRepository;
    @Autowired
    public PostService(@Qualifier("mapPostRepository") PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public long register(Post post) {
        if(Objects.isNull(post.getWriteTime())){
            post.setWriteTime(LocalDateTime.now());
        }
        postRepository.getPostMap().put((long)postRepository.getIndex().incrementAndGet(),post);
        return postRepository.getIndex().get();
    }


    public void modify(Post post) {
        postRepository.getPostMap().replace(post.getId(),post);
    }


    public void remove(long id) {
        postRepository.getPostMap().remove(id);
    }


    public Post getPost(long id) {
        return postRepository.getPostMap().get(id);
    }


    public List<Post> getPosts() {
        List<Post> postList = new ArrayList<>(postRepository.getPostMap().values());
        return postList;
    }

    public int getTotalCount(){
        List<Post> posts = getPosts();
        return posts.size();
    }
    public Page<Post> getPagedPosts(int page, int size){
        Page page2 = new Page();
        page2.setPageNumber(page);
        page2.setPageSize(size);
        page2.setTotalPageCount(getPageNum(getTotalCount(),size));
        page2.setTotalCount(getTotalCount());
        page2.setList(paging(page,size));
        return page2;
    }
    public int getPageNum(int totalSize, int size){
        int totalPage=0;
        totalPage =  ( (totalSize - 1) / size ) + 1;
        return totalPage;
    }
    public List<Post> paging(int page, int size){
        List<Post> posts = getPosts();
        List<Post> pagePosts = new ArrayList<>();

        int start = (page-1)*size+1;
        int end = page*size;
        if(end>getTotalCount()){
            end = getTotalCount();
        }
        for(int i=start-1;i<end;i++){
            pagePosts.add(posts.get(i));
        }
        return pagePosts;
    }
}