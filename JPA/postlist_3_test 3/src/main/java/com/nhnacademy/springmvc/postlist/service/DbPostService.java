package com.nhnacademy.springmvc.postlist.service;

import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbPostService {
    private final PostMapper postMapper;
    public void register(Post post){
        postMapper.save(post);
    }
    public void update(Post post){
        postMapper.update(post);
    }
    public void remove(long id){
        postMapper.remove(id);
    }
    public Post getPost(long id){
        return postMapper.getPost(id);
    }
    public List<Post> getPosts(){
        return postMapper.getPosts();
    }
    public long getTotalCount(){
        return postMapper.getTotalCount();
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
    public int getPageNum(long totalSize, int size){
        int totalPage=0;
        totalPage = (int) (( (totalSize - 1) / size ) + 1);
        return totalPage;
    }
    public List<Post> paging(int page, int size){
        List<Post> posts = getPosts();
        List<Post> pagePosts = new ArrayList<>();

        int start = (page-1)*size+1;
        long end = page*size;
        if(end>getTotalCount()){
            end = getTotalCount();
        }
        for(int i=start-1;i<end;i++){
            pagePosts.add(posts.get(i));
        }
        return pagePosts;
    }
}
