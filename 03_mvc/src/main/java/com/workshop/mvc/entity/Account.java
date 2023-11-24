package com.workshop.mvc.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Account {

    private int number;
    private BigDecimal balance;

    public Account() {
    }

    public Account(int number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
