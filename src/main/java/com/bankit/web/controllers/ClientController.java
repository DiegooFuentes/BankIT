package com.bankit.web.controllers;

import com.bankit.web.dtos.ClientDTO;
import com.bankit.web.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClients();

    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        return clientService.register(firstName, lastName, email, password);

    }

    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        return clientService.getCurrentClient(authentication.getName());
    }

}
