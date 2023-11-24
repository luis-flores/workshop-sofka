package com.workshop.mvc.repository;

import com.workshop.mvc.entity.Transaction;

import java.util.List;

public interface ITransactionRepository {
    Transaction apply(Transaction transaction);
    List<Transaction> getAppliedTransactions();
}
