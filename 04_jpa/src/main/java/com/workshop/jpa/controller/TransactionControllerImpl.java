package com.workshop.jpa.controller;

import com.workshop.jpa.dto.TransactionDTO;
import com.workshop.jpa.service.ITransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionControllerImpl implements ITransactionController {
    private final ITransactionService transactionService;

    @PostMapping
    @Override
    public ResponseEntity save(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data");
        }

        transactionDTO = transactionService.save(transactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<TransactionDTO>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }
}
