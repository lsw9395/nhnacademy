package com.nhnacademy.springmvc.postlist.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvc.postlist.domain.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Order(1)
public class JsonUserRepository implements UserRepository {
    Map<String,User> userMap = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/iseong-un/Desktop/postlist_3/src/json/user.json";//맥용
    //private static final String JSON_FILE_PATH="C:\\Users\\이성운\\Desktop\\postlist_3\\src\\json\\user.json";//윈도우용
    private static List<User> users = new ArrayList<>();
    public JsonUserRepository() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private synchronized List<User> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        File file = new File(JSON_FILE_PATH);
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            users = objectMapper.readValue(bufferedReader, new TypeReference<List<User>>() {});
            return users;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<User> studentList){
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
    public Map<String, User> getUserMap() {
        List<User> list = readJsonFile();
        for(User user:list){
            userMap.put(user.getId(),user);
        }
        return userMap;
    }

    @Override
    public void setUserMap() {
        writeJsonFile(new ArrayList<>(userMap.values()));
    }
}
