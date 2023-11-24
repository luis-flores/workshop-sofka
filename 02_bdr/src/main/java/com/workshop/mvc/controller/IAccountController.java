package com.workshop.mvc.controller;

import com.workshop.mvc.dto.AccountDTO;
import com.workshop.mvc.entity.Account;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface IAccountController {
    ResponseEntity<AccountDTO> getAccount(Integer number);
}
