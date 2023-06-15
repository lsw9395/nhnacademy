package com.nhnacademy.edu.springframework.project.repository;

import java.util.List;

public interface Scores {
    boolean load();

    List<Score> findAll();
}
