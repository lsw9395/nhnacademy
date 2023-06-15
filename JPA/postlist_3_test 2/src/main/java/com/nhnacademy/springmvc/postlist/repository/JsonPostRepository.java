package com.nhnacademy.springmvc.postlist.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvc.postlist.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Order(1)
@Slf4j
public class JsonPostRepository implements PostRepository{
    Map<Long,Post> postMap = new HashMap<>();
    AtomicLong index = new AtomicLong(0);
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/iseong-un/Downloads/postlist_3_test/src/json/post.json";//맥
    //private static final String JSON_FILE_PATH="C:\\Users\\이성운\\Desktop\\postlist_3_test\\src\\json\\post.json";//윈도우
    private static List<Post> posts = new ArrayList<>();
    public JsonPostRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private synchronized List<Post> readJsonFile(){
        File file = new File(JSON_FILE_PATH);
        log.info(file.getName());
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            posts = objectMapper.readValue(bufferedReader, new TypeReference<List<Post>>() {});
            if(Objects.isNull(posts)&&index.get()==0){
                index.set(posts.get(posts.size()-1).getId());
            }
            return posts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Post> studentList){

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
    public AtomicLong getIndex() {
        return index;
    }

    @Override
    public void setIndex(AtomicLong index) {
        this.index=index;
    }

    @Override
    public Map<Long, Post> getPostMap() {
        List<Post> list = readJsonFile();
        for(Post post:list){
            if(post.getId()!=0){
                postMap.put(post.getId(),post);
            }
        }
        return postMap;
    }

    public void setPostMap() {
        writeJsonFile(new ArrayList<>(postMap.values()));
    }
}
