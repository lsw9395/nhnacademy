package com.nhnacademy.notice_board.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonUserRepositoty implements UserRepository {
    AtomicInteger index = new AtomicInteger(0);
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/iseong-un/Desktop/board_step2_2/notice_board_2/src/json/user.json";//맥용
    //private static final String JSON_FILE_PATH="C:/Users/이성운/Desktop/board_step2/board_step1/notice_board_2/src/json/user.json";//윈도우용
    private static List<User> users = new ArrayList<>();
    public JsonUserRepositoty() {
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
    public void add(User user) {
        List<User> users2 = readJsonFile();
        for(User user1:users2){
            if(user1.getId().equals(user.getId())){
                throw new RuntimeException("이미 존재하는 아이디입니다.");
            }
        }
        users2.add(user);
        writeJsonFile(users2);
    }

    @Override
    public void modify(User user) {
        List<User> users = readJsonFile();
        for(User user2:users){
            if(user2.getId().equals(user.getId())){
                user2.setId(user.getId());
                user2.setName(user.getName());
                user2.setPassword(user.getPassword());
                user2.setProfileFileName(user.getProfileFileName());
                writeJsonFile(users);
                return;
            }
        }
    }

    @Override
    public void remove(String id) {
        List<User> users = readJsonFile();
        for(User user:users){
            if(user.getId().equals(id)){
                users.remove(user);
                writeJsonFile(users);
                return;
            }
        }
    }

    @Override
    public User getUser(String id) {
        List<User> users = readJsonFile();
        for(User user:users){
            if(user.getId().equals(id)){
                users.remove(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = readJsonFile();
        return users;
    }
}
