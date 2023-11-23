package com.workshop.transactionmongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Arrays;

@Document
public class Transaction {
    @Id
    private String id;
    private String type;
    private BigDecimal amount;
    private BigDecimal cost;

    @DBRef
    private Client client;

    public Transaction() {
    }

    public Transaction(String id, String type, BigDecimal amount, BigDecimal cost, Client client) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.cost = cost;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!Arrays.asList(new String[]{"DepositoSucursal", "DepositoCuenta", "DepositoCajero", "CompraFisica", "CompraWeb", "RetiroCajero"}).contains(type)) {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        switch (type) {
            case "DepositoSucursal":
            case "DepositoCuenta":
            case "DepositoCajero":
                this.amount = amount;
                break;
            case "CompraFisica":
            case "CompraWeb":
            case "RetiroCajero":
                this.amount = amount.multiply(new BigDecimal(-1));
                break;
        }
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        if (cost != null) {
            this.cost = cost;
            return;
        }

        switch (type) {
            case "DepositoSucursal": setCost(new BigDecimal(0)); break;
            case "DepositoCuenta": setCost(new BigDecimal("1.5")); break;
            case "DepositoCajero": setCost(new BigDecimal(2)); break;
            case "CompraFisica": setCost(new BigDecimal(2)); break;
            case "CompraWeb": setCost(new BigDecimal(5)); break;
            case "RetiroCajero": setCost(new BigDecimal(1)); break;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
