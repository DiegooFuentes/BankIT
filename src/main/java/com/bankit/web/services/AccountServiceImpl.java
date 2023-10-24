package com.bankit.web.services;

import com.bankit.web.dtos.AccountDTO;
import com.bankit.web.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
    }

    @Override
    public AccountDTO getAccount(Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }
}
