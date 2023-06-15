package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class GradeQueryServiceTest {

    @Test
    public void getScoreByStudentName() {
        boolean result = true;
        Score score1 = new Score(1,30);
        Score score2 = new Score(3,70);
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        DefaultGradeQueryService service = new DefaultGradeQueryService();
        List<Score> actual = service.getScoreByStudentName("A");
        List<Score> expect = Arrays.asList(score1,score2);
        for(int i = 0; i <expect.size();i++){
            if(!(expect.get(i).getStudentSeq() == actual.get(i).getStudentSeq())){
                System.out.println(expect.get(i));
                System.out.println(actual.get(i));
                result = false;
            }
        }
        Assertions.assertTrue(result);
    }

    @Test
    public void getScoreByStudentSeq() {
        boolean result = true;
        Score expect= new Score(1,30);
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
        DefaultGradeQueryService service = new DefaultGradeQueryService();
        Score actual = service.getScoreByStudentSeq(1);
        if(!(expect.getScore()==actual.getScore()&&expect.getStudentSeq()==actual.getStudentSeq())){
            System.out.println(expect);
            System.out.println(actual);
            result = false;
        }
        Assertions.assertTrue(result);
    }
}