package com.workshop.jpa.controller;

import com.workshop.jpa.dto.ClientDTO;
import org.springframework.http.ResponseEntity;

public interface IClientController {
    ResponseEntity<?> getClient(Long id);
}
