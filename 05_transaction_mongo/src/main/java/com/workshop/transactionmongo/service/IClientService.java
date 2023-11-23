package com.workshop.transactionmongo.service;

import com.workshop.transactionmongo.dto.ClientDTO;

import java.util.List;

public interface IClientService {
    List<ClientDTO> findAll();
}
