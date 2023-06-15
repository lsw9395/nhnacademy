package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {
    private final Scores csvScores;
    private final Students csvStudents;
    @Autowired
    public CsvDataLoadService(@Qualifier("csvScores") Scores csvScores,@Qualifier("csvStudents") Students csvStudents){
        this.csvScores = csvScores;
        this.csvStudents = csvStudents;
    }
    @Override
    public boolean loadAndMerge() {
        csvScores.load();
        csvStudents.load();
        csvStudents.merge(csvScores.findAll());
        return true;
    }
}
