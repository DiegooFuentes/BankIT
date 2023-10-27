package com.bankit.web.services;

import com.bankit.web.dtos.AccountDTO;
import com.bankit.web.models.Account;
import com.bankit.web.models.Client;
import com.bankit.web.repositories.AccountRepository;
import com.bankit.web.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bankit.web.utils.CardUtil.generateRandomNumber;
import static java.util.stream.Collectors.toList;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
    }

    @Override
    public AccountDTO getAccount(Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<Object> createAccount(String email) {
        Client client = clientRepository.findByEmail(email);

        if (client.getAccounts().size() > 3) {
            return new ResponseEntity<>("Max accounts is 3", HttpStatus.FORBIDDEN);
        } else {
            String accountNumber = generateRandomNumber(2);
            Account account = new Account("VIN" + accountNumber, LocalDateTime.now(), 0.0);
            account.setClient(client);
            accountRepository.save(account);
            return new ResponseEntity<>("Account created", HttpStatus.CREATED);
        }

    }
}
