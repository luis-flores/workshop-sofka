package com.workshop.transactionmongo.controller;

import com.workshop.transactionmongo.documents.Account;
import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.AccountDTO;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.dto.TransactionDTO;
import com.workshop.transactionmongo.service.IClientService;
import com.workshop.transactionmongo.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class)
@RestClientTest(TransactionControllerImpl.class)
public class TransactionControllerImplTests {

    @InjectMocks
    private TransactionControllerImpl transactionController;
    @MockBean @Mock
    private ITransactionService transactionService;

    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void findAllTest() throws Exception {
        List<TransactionDTO> transactions = new ArrayList<>();
        AccountDTO account = new AccountDTO(new BigDecimal("5000.55"));
        ClientDTO client = new ClientDTO("id", "client-name", "client-email", account);
        transactions.add(new TransactionDTO("id", "type", new BigDecimal(10), new BigDecimal(2), client));
        when(transactionService.findAll()).thenReturn(transactions);
        mockServer.expect(requestTo("/transactions"))
            .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        ResponseEntity<List<TransactionDTO>> response = transactionController.findAll();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isEmpty();
        mockServer.verify();
    }
}
