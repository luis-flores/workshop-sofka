package com.workshop.mvc.controller;

import com.workshop.mvc.dto.AccountDTO;
import com.workshop.mvc.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountControllerImpl implements IAccountController {
    private final IAccountService accountService;

    @GetMapping("/{number}")
    @Override
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer number) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setNumber(number);
        accountDTO = accountService.getAccount(accountDTO);
        return ResponseEntity.ok(accountDTO);
    }
}
