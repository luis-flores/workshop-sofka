package com.workshop.transactionmongo.controller;

import org.springframework.http.ResponseEntity;

public interface IClientController {
    ResponseEntity<?> getClients();
}
