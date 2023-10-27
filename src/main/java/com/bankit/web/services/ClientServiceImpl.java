package com.bankit.web.services;

import com.bankit.web.dtos.ClientDTO;
import com.bankit.web.models.Account;
import com.bankit.web.models.Client;
import com.bankit.web.repositories.AccountRepository;
import com.bankit.web.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bankit.web.utils.CardUtil.generateRandomNumber;
import static java.util.stream.Collectors.toList;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());
    }

    @Override
    public ClientDTO getClient(Long id) {

        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);

    }

    @Override
    public ResponseEntity<Object> register(String firstName, String lastName, String email, String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        //Client client = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        //String accountNumber = generateRandomNumber(2);
        Account account = new Account("VIN" + generateRandomNumber(2), LocalDateTime.now(), 0.0);
        account.setClient(clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password))));
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ClientDTO getCurrentClient(String email) {
        /*
        Client = clientRepository.findByEmail(email);
        return clientRepository.findById(client.getId()).map(ClientDTO::new).orElse(null);
            //We use map when it is an optional
         */
        return new ClientDTO(clientRepository.findByEmail(email));
    }

}
