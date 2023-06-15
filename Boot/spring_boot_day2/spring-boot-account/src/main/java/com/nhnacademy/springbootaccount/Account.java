package com.nhnacademy.springbootaccount;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@EqualsAndHashCode
@Entity
@Setter
@ToString
public class Account {
    @Id
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private int balance;

    public Account() {
    }

    public Account(Long id, String number, int balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }
}
