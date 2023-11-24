package com.workshop.jpa.controller;

import com.workshop.jpa.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ITransactionController {
    ResponseEntity save(TransactionDTO transaction, BindingResult result);
    ResponseEntity<List<TransactionDTO>> findAll();
}
