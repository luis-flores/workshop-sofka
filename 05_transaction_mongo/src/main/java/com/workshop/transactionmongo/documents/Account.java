package com.workshop.transactionmongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Account {

    @Id
    private String id;
    private BigDecimal balance;
    private Client client;

    public Account() {
    }

    public Account(String id, BigDecimal balance, Client client) {
        this.id = id;
        this.balance = balance;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
