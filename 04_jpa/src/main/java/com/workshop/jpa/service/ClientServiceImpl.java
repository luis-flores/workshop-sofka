package com.workshop.jpa.service;

import com.workshop.jpa.dto.ClientDTO;
import com.workshop.jpa.entity.Client;
import com.workshop.jpa.repository.IClientRepository;
import com.workshop.jpa.util.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final IClientRepository clientRepository;

    @Override
    public ClientDTO getClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        ClientMapper clientMapper = new ClientMapper();
        if (client.isPresent()) {
            return clientMapper.getClient(client.get());
        } else {
            throw new IllegalArgumentException("Client " + id + " not found ");
        }
    }
}
