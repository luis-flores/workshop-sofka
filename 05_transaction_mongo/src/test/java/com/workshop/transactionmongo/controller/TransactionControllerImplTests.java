package com.workshop.transactionmongo.controller;

import com.workshop.transactionmongo.documents.Account;
import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.AccountDTO;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.dto.TransactionDTO;
import com.workshop.transactionmongo.service.IClientService;
import com.workshop.transactionmongo.service.ITransactionService;
import com.workshop.transactionmongo.service.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

//@RunWith(SpringRunner.class)
//@RestClientTest(TransactionControllerImpl.class)
@ExtendWith(MockitoExtension.class)
public class TransactionControllerImplTests {

//    @InjectMocks
    private TransactionControllerImpl transactionController;
//    @Mock
    private ITransactionService transactionService;

//    private RestTemplate restTemplate;
//    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
//        restTemplate = new RestTemplate();
//        mockServer = MockRestServiceServer.createServer(restTemplate);
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
//        mockServer.expect(requestTo("/transactions"))
//            .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        ResponseEntity<List<TransactionDTO>> response = transactionController.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEmpty();
//        mockServer.verify();
        Mockito.verify(transactionService).findAll();
    }
}
