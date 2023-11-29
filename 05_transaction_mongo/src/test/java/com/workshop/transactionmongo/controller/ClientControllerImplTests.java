package com.workshop.transactionmongo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.transactionmongo.dto.AccountDTO;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.service.IClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ClientControllerImpl.class /*, secure = false*/)
public class ClientControllerImplTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getClientsTest() throws Exception {
        List<ClientDTO> clients = new ArrayList<>();
        AccountDTO account = new AccountDTO(new BigDecimal("5000.55"));
        clients.add(new ClientDTO("id", "client-name", "client-email", account));
        when(clientService.findAll()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(clients)));
    }
}
