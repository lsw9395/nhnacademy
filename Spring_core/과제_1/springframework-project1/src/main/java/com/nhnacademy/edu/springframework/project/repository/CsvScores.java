package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvScores implements Scores {
    private static boolean isLoad = false;
    private final static CsvScores csvScores = new CsvScores();
    private final static List<Score> scoreList = new ArrayList<>();
    private CsvScores(){}

    public static Scores getInstance() {
        return csvScores;
    }

    private static void checkLoaded() {
        if (!isLoad) {
            throw new IllegalStateException("아직 데이터 업로드 안됨");
        }
    }
    @Override
    public void load() {
        if(!isLoad){
            try(InputStream inputStream = new ClassPathResource("data/score.csv").getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
                String line;
                while((line = reader.readLine())!=null){
                    String lines[] = line.split(",");
                    Score score = new Score(Integer.parseInt(lines[0]),Integer.parseInt(lines[1]));
                    scoreList.add(score);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                isLoad = true;
            }
        }
    }

    @Override
    public List<Score> findAll() {
        checkLoaded();
        return scoreList;
    }
}
