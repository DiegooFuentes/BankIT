package com.bankit.web.services;

import com.bankit.web.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    public List<ClientDTO> getClients();

    public ClientDTO getClient(Long id);

    public ResponseEntity<Object> register(String firstName, String lastName, String email, String password);

    public ClientDTO getCurrentClient(String email);

}
