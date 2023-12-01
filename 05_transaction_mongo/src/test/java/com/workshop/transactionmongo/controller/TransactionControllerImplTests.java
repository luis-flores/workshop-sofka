package com.workshop.transactionmongo.controller;

import com.workshop.transactionmongo.dto.AccountDTO;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.dto.TransactionDTO;
import com.workshop.transactionmongo.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerImplTests {

    private TransactionControllerImpl transactionController;
    private ITransactionService transactionService;

    @BeforeEach
    public void setUp() {
        transactionService = Mockito.mock(ITransactionService.class);
        transactionController = new TransactionControllerImpl(transactionService);
    }

    @Test
    public void findAllTest() throws Exception {
        List<TransactionDTO> transactions = new ArrayList<>();
        AccountDTO account = new AccountDTO(new BigDecimal("5000.55"));
        ClientDTO client = new ClientDTO("id", "client-name", "client-email", account);
        transactions.add(new TransactionDTO("id", "type", new BigDecimal(10), new BigDecimal(2), client));
        when(transactionService.findAll()).thenThrow(HttpServerErrorException.InternalServerError.class).thenReturn(transactions);

        ResponseEntity<List<TransactionDTO>> response = transactionController.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Mockito.verify(transactionService).findAll();
    }
}
