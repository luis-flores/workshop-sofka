package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.mapping.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private MongoTemplate mongoTemplate;
    private final ClientMapper clientMapper;

    public List<ClientDTO> findAll() {
        List<Client> clients = mongoTemplate.findAll(Client.class);
        return clientMapper.toDTOs(clients);
    }
}
