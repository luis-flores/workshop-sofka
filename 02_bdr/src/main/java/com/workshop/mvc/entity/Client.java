package com.workshop.mvc.entity;


import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
