package com.nhnacademy.edu.springframework.project.repository;

public class Student {
    private final int seq;
    private final String name;
    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore(){
        return this.score;
    }
    public int getSeq(){
        return this.seq;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}'+ '\n';
    }
}
