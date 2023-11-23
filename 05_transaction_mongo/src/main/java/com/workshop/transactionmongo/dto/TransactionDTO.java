package com.workshop.transactionmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private String id;
    private String type;
    private BigDecimal amount;
    private BigDecimal cost;
    private ClientDTO client;
}
