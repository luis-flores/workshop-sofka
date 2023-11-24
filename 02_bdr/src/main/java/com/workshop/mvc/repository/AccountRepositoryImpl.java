package com.workshop.mvc.repository;

import com.workshop.mvc.entity.Account;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepositoryImpl implements IAccountRepository {
    public final Map<Integer, Account> accounts;

    @Value("${initial.balance}")
    private BigDecimal initialBalance;

    public AccountRepositoryImpl(Account account) {
        this.accounts = new HashMap<>();
    }

    @Override
    public Account getAccount(Account account) {
        int number = account.getNumber();

        if (!accounts.containsKey(number)) {
            account.setBalance(initialBalance);
            accounts.put(number, account);
        }

        return accounts.get(number);
    }

    @Override
    public Account applyAmount(Account account, BigDecimal amount) {
        account = accounts.get(account.getNumber());
        BigDecimal currentBalance = account.getBalance();
        account.setBalance(currentBalance.add(amount));
        return account;
    }
}
