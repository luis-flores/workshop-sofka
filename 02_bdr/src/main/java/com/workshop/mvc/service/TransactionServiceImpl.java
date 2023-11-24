package com.workshop.mvc.service;

import com.workshop.mvc.dto.TransactionDTO;
import com.workshop.mvc.entity.Transaction;
import com.workshop.mvc.repository.ITransactionRepository;
import com.workshop.mvc.utils.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final ITransactionRepository transactionRepository;

    @Override
    public TransactionDTO apply(TransactionDTO transactionDTO) {
        TransactionMapper mapper = new TransactionMapper();
        Transaction transaction = mapper.getTransaction(transactionDTO);
        transaction = transactionRepository.apply(transaction);
        return mapper.getTransaction(transaction);
    }

    @Override
    public List<TransactionDTO> getAppliedTransactions() {
        TransactionMapper mapper = new TransactionMapper();
        return transactionRepository.getAppliedTransactions()
            .stream()
            .map(mapper::getTransaction)
            .collect(Collectors.toList());
    }
}
