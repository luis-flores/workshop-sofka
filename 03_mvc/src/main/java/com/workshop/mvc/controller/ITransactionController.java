package com.workshop.mvc.controller;

import com.workshop.mvc.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ITransactionController {
    ResponseEntity apply(TransactionDTO transaction, BindingResult result);
    ResponseEntity<List<TransactionDTO>> getAppliedTransactions();
}
