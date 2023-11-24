package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    TransactionDTO save(TransactionDTO transactionDTO);
    List<TransactionDTO> findAll();
    List<TransactionDTO> findTransactionsByClient(ClientDTO clientDTO);
}
