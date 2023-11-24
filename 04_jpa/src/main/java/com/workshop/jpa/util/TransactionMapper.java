package com.workshop.jpa.util;

import com.workshop.jpa.dto.TransactionDTO;
import com.workshop.jpa.entity.Account;
import com.workshop.jpa.entity.Transaction;

public class TransactionMapper {
    public TransactionDTO getTransaction(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setCost(transaction.getCost());

        if (transaction.getAccount()!= null) {
            AccountMapper accountMapper = new AccountMapper();
            transactionDTO.setAccount(accountMapper.getAccount(transaction.getAccount()));
        }
        return transactionDTO;
    }

    public Transaction getTransaction(TransactionDTO transactionDTO) {
        AccountMapper accountMapper = new AccountMapper();
        Account account = null;
        if (transactionDTO.getAccount()!= null) {
            account = accountMapper.getAccount(transactionDTO.getAccount());
        }

        Transaction transaction = new Transaction(
            transactionDTO.getType(),
            transactionDTO.getAmount(),
            transactionDTO.getCost(),
            account
        );

        return transaction;
    }
}
