package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.documents.Transaction;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.dto.TransactionDTO;
import com.workshop.transactionmongo.mapping.TransactionMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
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

    public List<TransactionDTO> findTransactionsByClient(ClientDTO clientDTO) {

        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.addFields().addFieldWithValue("clientId", "$client.$id").build(),
            Aggregation.match(Criteria.where("clientId").is(new ObjectId (clientDTO.getId())))
        );
        AggregationResults<Transaction> results = mongoTemplate.aggregate(aggregation, "transaction", Transaction.class);

        List<Transaction> transactions = results.getMappedResults();
        return transactionMapper.toDTOs(transactions);
    }
}
