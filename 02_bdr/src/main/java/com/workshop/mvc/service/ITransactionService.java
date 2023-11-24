package com.workshop.mvc.service;

import com.workshop.mvc.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    TransactionDTO apply(TransactionDTO transactionDTO);
    List<TransactionDTO> getAppliedTransactions();
}
