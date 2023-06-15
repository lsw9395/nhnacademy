package com.nhnacademy.edu.springframework.project.repository;



import com.nhnacademy.edu.springframework.project.config.MainConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;


public class ScoresTest {

    Scores csvScores;
    @BeforeEach
    void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        csvScores = context.getBean("csvScores",Scores.class);
    }
    @Test
    void load() {
        csvScores.load();
        assertNotNull(csvScores.findAll());
    }

    @Test
    void findAll() {
        boolean result = true;
        Score score1 = new Score(1,30);
        Score score2 = new Score(2,80);
        Score score3 = new Score(3,70);
        List<Score> expect = Arrays.asList(score1,score2,score3);
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