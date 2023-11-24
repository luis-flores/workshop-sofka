package com.workshop.mvc.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Integer number;
    private BigDecimal balance;

    @OneToOne(mappedBy = "account")
    private Client client;

    public Account() {
    }

    public Account(Integer number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public Account(Long id, Integer number, BigDecimal balance, Client client) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
