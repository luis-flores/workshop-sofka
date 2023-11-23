package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.documents.Transaction;
import com.workshop.transactionmongo.dto.TransactionDTO;
import com.workshop.transactionmongo.mapping.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private MongoTemplate mongoTemplate;
    private final TransactionMapper transactionMapper;

    public List<TransactionDTO> findAll() {
        List<Transaction> transactions = mongoTemplate.findAll(Transaction.class);
        return transactionMapper.toDTOs(transactions);
    }

    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.fromDTO(transactionDTO);
        transaction = mongoTemplate.save(transaction);
        return transactionMapper.toDTO(transaction);
    }
}
