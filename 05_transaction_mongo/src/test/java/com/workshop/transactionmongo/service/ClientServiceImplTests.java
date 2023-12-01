package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.documents.Account;
import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.AccountDTO;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.mapping.ClientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTests {
    @InjectMocks
    private ClientServiceImpl clientService;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private ClientMapper clientMapper;

    @Test
    public void findAllTest() {
        Account account = new Account(new BigDecimal("5000.55"));
        Client client = new Client("id", "client-name", "client-email", account);

        List<Client> clients = new ArrayList<Client>();
        clients.add(client);

        AccountDTO accountDTO = new AccountDTO(account.getBalance());
        List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
        clientDTOs.add(new ClientDTO("id", "client-name", "client-email", accountDTO));

        when(mongoTemplate.findAll(Client.class))
            .thenReturn(clients);
        when(clientMapper.toDTOs(clients))
            .thenReturn(clientDTOs);

        List<ClientDTO> responseClients = clientService.findAll();
        assertThat(responseClients.size()).isEqualTo(1);
        ClientDTO responseClient = responseClients.get(0);
        assertThat(responseClient.getId()).isEqualTo(client.getId());
    }
}
