package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DataParserTest {
    @Test
    void parse(){
        DataParser parser = Mockito.mock(DataParser.class);
        parser.parse("test");
        Mockito.verify(parser, Mockito.times(1)).parse("test");
    }
}
