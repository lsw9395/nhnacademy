package com.nhnacademy.notice_board.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class JsonPostRepository implements PostRepository{
    AtomicInteger index = new AtomicInteger(0);
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/iseong-un/Desktop/board_step2_2/notice_board_2/src/json/post.json";//맥
    //private static final String JSON_FILE_PATH="C:/Users/이성운/Desktop/board_step2/board_step1/notice_board_2/src/json/post.json";//윈도우
    private static List<Post> posts = new ArrayList<>();
    public JsonPostRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


    }

    private synchronized List<Post> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        File file = new File(JSON_FILE_PATH);
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            posts = objectMapper.readValue(bufferedReader, new TypeReference<List<Post>>() {});
            return posts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Post> studentList){
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);

        try(
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long register(Post post) {
        if(Objects.isNull(post.getWriteTime())){
            post.setWriteTime(LocalDateTime.now());
        }

        List<Post> posts = readJsonFile();
        post.setId(index.incrementAndGet());
        posts.add(post);
        writeJsonFile(posts);
        return index.get();
    }

    @Override
    public void modify(Post post) {
        List<Post> posts = readJsonFile();
        for(Post post1:posts){
            if(post1.getId()==post.getId()){
                post1.setTitle(post.getTitle());
                post1.setContent(post.getContent());
                post1.setWriteTime(post.getWriteTime());
                post1.setId(post.getId());
                post1.setViewCount(post.getViewCount());
                post1.setWriterUserId(post.getWriterUserId());
            }
        }
        writeJsonFile(posts);
    }

    @Override
    public void remove(long id) {
        List<Post> posts = readJsonFile();
        for(Post post :posts){
            if(post.getId()==id){
                posts.remove(post);
                writeJsonFile(posts);
                return;
            }
        }
    }

    @Override
    public Post getPost(long id) {
        List<Post> posts = readJsonFile();
        for(Post post :posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = readJsonFile();
        return posts;
    }
    public int getTotalCount(){
        List<Post> posts = readJsonFile();
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
        totalPage = totalSize/size;
        if(totalPage%size!=0){
            totalPage++;
        }
        return totalPage;
    }
    public List<Post> paging(int page, int size){
        List<Post> posts = readJsonFile();
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
