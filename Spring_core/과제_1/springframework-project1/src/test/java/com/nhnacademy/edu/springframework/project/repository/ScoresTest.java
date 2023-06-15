package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ScoresTest {
    private static List<Score> scoreList = new ArrayList<>();
    @Test
    void load() {
        try(InputStream inputStream = new ClassPathResource("data/score.csv").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
            String line;
            while((line = reader.readLine())!=null){
                String lines[] = line.split(",");
                Score score = new Score(Integer.parseInt(lines[0]),Integer.parseInt(lines[1]));
                scoreList.add(score);
                System.out.println(score);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll() {
        boolean result = true;
        Score score1 = new Score(1,30);
        Score score2 = new Score(2,80);
        Score score3 = new Score(3,70);
        List<Score> expect = Arrays.asList(score1,score2,score3);
        Scores csvScores = CsvScores.getInstance();
        csvScores.load();
        List<Score> actual = csvScores.findAll();
        for(int i = 0; i <expect.size();i++){
            if(!(expect.get(i).getStudentSeq()==actual.get(i).getStudentSeq() && expect.get(i).getScore()==actual.get(i).getScore())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }
}