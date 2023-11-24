package com.workshop.jpa.controller;

import com.workshop.jpa.dto.ClientDTO;
import com.workshop.jpa.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientControllerImpl implements IClientController {
    private final IClientService clientService;

    @Override
    @RequestMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        try {
            ClientDTO clientDTO = clientService.getClient(id);

            return ResponseEntity.ok(clientDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting client");
        }
    }
}
