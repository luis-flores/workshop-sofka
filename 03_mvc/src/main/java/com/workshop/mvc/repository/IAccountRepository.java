package com.workshop.mvc.repository;

import com.workshop.mvc.entity.Account;

import java.math.BigDecimal;

public interface IAccountRepository {
    Account getAccount(Account account);
    Account applyAmount(Account account, BigDecimal amount);
}
