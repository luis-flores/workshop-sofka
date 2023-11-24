package com.workshop.jpa.service;

import com.workshop.jpa.dto.ClientDTO;

public interface IClientService {
    ClientDTO getClient(Long id);
}
