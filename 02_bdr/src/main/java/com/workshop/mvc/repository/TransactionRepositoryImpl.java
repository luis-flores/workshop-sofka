package com.workshop.mvc.repository;

import com.workshop.mvc.entity.Account;
import com.workshop.mvc.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements ITransactionRepository {
    private final IAccountRepository accountRepository;
    private final List<Transaction> appliedTransactions = new ArrayList<>();

    @Override
    public Transaction apply(Transaction transaction) {
        Account account = accountRepository.applyAmount(
            transaction.getAccount(),
            transaction.getAmount().subtract(transaction.getCost())
        );
        transaction.setAccount(account);
        appliedTransactions.add(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getAppliedTransactions() {
        return appliedTransactions;
    }
}
