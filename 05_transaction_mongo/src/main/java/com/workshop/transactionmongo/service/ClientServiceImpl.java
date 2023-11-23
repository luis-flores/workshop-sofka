package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.ClientDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private MongoTemplate mongoTemplate;

    public List<ClientDTO> findAll() {
        List<Client> clients = mongoTemplate.findAll(Client.class);
        return clients.stream()
                .map(client -> new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getEmail()
                ))
                .toList();
    }
}
