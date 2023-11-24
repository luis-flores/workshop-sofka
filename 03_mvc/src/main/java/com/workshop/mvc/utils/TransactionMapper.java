package com.workshop.mvc.utils;

import com.workshop.mvc.dto.TransactionDTO;
import com.workshop.mvc.entity.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;

public class TransactionMapper {

    public Transaction getTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(
            transactionDTO.getAccount(),
            transactionDTO.getType(),
            transactionDTO.getAmount()
        );

        return transaction;
    }

    public TransactionDTO getTransaction(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO(
            transaction.getAccount(),
            transaction.getType(),
            transaction.getAmount(),
            transaction.getCost()
        );

        return transactionDTO;
    }
}
