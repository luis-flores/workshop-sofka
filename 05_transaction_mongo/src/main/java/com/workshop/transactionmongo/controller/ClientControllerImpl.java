package com.workshop.transactionmongo.controller;

import com.workshop.transactionmongo.dto.ClientDTO;
import com.workshop.transactionmongo.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientControllerImpl implements IClientController {
    private final IClientService clientService;

    @GetMapping("/")
    @Override
    public ResponseEntity<?> getClients() {
        try {
            List<ClientDTO> clients = clientService.findAll();

            return ResponseEntity.ok(clients);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting clients");
        }
    }
}
