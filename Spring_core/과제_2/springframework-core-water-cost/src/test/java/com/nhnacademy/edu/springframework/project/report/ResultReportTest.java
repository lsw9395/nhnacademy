package com.nhnacademy.edu.springframework.project.report;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


public class ResultReportTest {
    @Test
    void report(){
        ResultReport reporter = Mockito.mock(ResultReport.class);

        List<WaterBill> topFive = Arrays.asList(
                new WaterBill("청송군", "가정용", 220)
        );
        reporter.report(topFive);
        // 검증
        Mockito.verify(reporter, Mockito.times(1)).report(topFive);
    }
}
