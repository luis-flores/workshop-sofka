package com.workshop.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Arrays;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    private String type;
    private BigDecimal amount;
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {
    }

    public Transaction(String type, BigDecimal amount, BigDecimal cost, Account account) {
        setAccount(account);
        setType(type);

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

    public Transaction(Long id, String type, BigDecimal amount, BigDecimal cost, Account account) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.cost = cost;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
