package com.workshop.mvc.controller;

import com.workshop.mvc.dto.TransactionDTO;
import com.workshop.mvc.entity.Transaction;
import com.workshop.mvc.service.ITransactionService;
import com.workshop.mvc.utils.TransactionMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionControllerImpl implements ITransactionController{
    private final ITransactionService transactionService;

    @PostMapping("/apply")
    @Override
    public ResponseEntity<?> apply(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data");
        }

        transactionDTO = transactionService.apply(transactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<TransactionDTO>> getAppliedTransactions() {
        return ResponseEntity.ok(transactionService.getAppliedTransactions());
    }
}
