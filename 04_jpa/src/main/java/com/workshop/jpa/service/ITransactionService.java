package com.workshop.jpa.service;

import com.workshop.jpa.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    TransactionDTO save(TransactionDTO transactionDTO);
    List<TransactionDTO> findAll();
}
