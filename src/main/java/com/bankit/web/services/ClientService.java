package com.bankit.web.services;

import com.bankit.web.dtos.ClientDTO;

import java.util.List;

public interface ClientService {

    public List<ClientDTO> getClients();

    public ClientDTO getClient(Long id);

}
