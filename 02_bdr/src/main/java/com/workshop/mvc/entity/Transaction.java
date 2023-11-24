package com.workshop.mvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Arrays;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Account account;
    private String type;
    private BigDecimal amount;
    private BigDecimal cost;

    public Transaction() {
    }

    public Transaction(Account account, String type, BigDecimal amount) {
        this.setAccount(account);
        this.setType(type);

        switch (type) {
            case "DepositoSucursal":
            case "DepositoCuenta":
            case "DepositoCajero":
                setAmount(amount);
                break;
            case "CompraFisica":
            case "CompraWeb":
            case "RetiroCajero":
                setAmount(amount.multiply(new BigDecimal(-1)));
                break;
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

    public Transaction(Long id, Account account, String type, BigDecimal amount, BigDecimal cost) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
