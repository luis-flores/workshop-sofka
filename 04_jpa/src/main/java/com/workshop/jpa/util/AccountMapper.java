package com.workshop.jpa.util;

import com.workshop.jpa.dto.AccountDTO;
import com.workshop.jpa.entity.Account;

public class AccountMapper {
    public AccountDTO getAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }

    public static Account getAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        return account;
    }
}
