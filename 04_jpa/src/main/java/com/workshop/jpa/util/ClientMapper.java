package com.workshop.jpa.util;

import com.workshop.jpa.dto.ClientDTO;
import com.workshop.jpa.entity.Client;

public class ClientMapper {
    public ClientDTO getClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());

        if (client.getAccount()!= null) {
            AccountMapper accountMapper = new AccountMapper();
            clientDTO.setAccount(accountMapper.getAccount(client.getAccount()));
        }
        return clientDTO;
    }
}
