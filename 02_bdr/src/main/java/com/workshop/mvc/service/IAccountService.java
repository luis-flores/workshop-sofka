package com.workshop.mvc.service;

import com.workshop.mvc.dto.AccountDTO;
import com.workshop.mvc.entity.Account;

import java.math.BigDecimal;

public interface IAccountService {
    AccountDTO getAccount(AccountDTO account);
    AccountDTO applyAmount(AccountDTO account, BigDecimal amount);
}
