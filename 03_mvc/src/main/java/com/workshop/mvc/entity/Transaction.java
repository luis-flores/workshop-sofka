package com.workshop.mvc.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class Transaction {
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
