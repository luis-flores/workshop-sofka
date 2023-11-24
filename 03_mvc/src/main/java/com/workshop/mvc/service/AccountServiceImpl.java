package com.workshop.mvc.service;

import com.workshop.mvc.dto.AccountDTO;
import com.workshop.mvc.entity.Account;
import com.workshop.mvc.repository.IAccountRepository;
import com.workshop.mvc.utils.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository accountRepository;

    @Override
    public AccountDTO getAccount(AccountDTO accountDTO) {
        AccountMapper mapper = new AccountMapper();
        Account account = accountRepository.getAccount(mapper.getAccount(accountDTO));
        return mapper.getAccount(account);
    }

    @Override
    public AccountDTO applyAmount(AccountDTO accountDTO, BigDecimal amount) {
        AccountMapper mapper = new AccountMapper();
        Account account = accountRepository.applyAmount(mapper.getAccount(accountDTO), amount);
        return mapper.getAccount(account);
    }
}
