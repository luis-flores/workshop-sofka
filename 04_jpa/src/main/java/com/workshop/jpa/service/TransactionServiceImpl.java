package com.workshop.jpa.service;

import com.workshop.jpa.dto.AccountDTO;
import com.workshop.jpa.dto.TransactionDTO;
import com.workshop.jpa.entity.Account;
import com.workshop.jpa.entity.Transaction;
import com.workshop.jpa.repository.IAccountRepository;
import com.workshop.jpa.repository.ITransactionRepository;
import com.workshop.jpa.util.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final ITransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        TransactionMapper mapper = new TransactionMapper();
        Transaction transaction = mapper.getTransaction(transactionDTO);

        /*
        AccountDTO accountDTO = transactionDTO.getAccount();
        if (accountDTO!= null) {
            Optional<Account> account = accountRepository.findById(accountDTO.getId());
            transaction.setAccount(account.get());
        }
        */

        transaction = transactionRepository.save(transaction);
        return mapper.getTransaction(transaction);
    }

    @Override
    public List<TransactionDTO> findAll() {
        TransactionMapper mapper = new TransactionMapper();
        return transactionRepository.findAll()
                .stream()
                .map(mapper::getTransaction)
                .collect(Collectors.toList());
    }
}
