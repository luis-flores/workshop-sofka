package com.workshop.mvc.utils;

import com.workshop.mvc.dto.AccountDTO;
import com.workshop.mvc.entity.Account;
import com.workshop.mvc.entity.Transaction;

public class AccountMapper {
    public Account getAccount(AccountDTO accountDTO) {
        Account account = new Account(
            accountDTO.getNumber(),
            accountDTO.getBalance()
        );

        return account;
    }

    public AccountDTO getAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO(
                account.getNumber(),
                account.getBalance()
        );

        return accountDTO;
    }
}
