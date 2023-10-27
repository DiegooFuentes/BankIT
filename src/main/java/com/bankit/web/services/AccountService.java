package com.bankit.web.services;

import com.bankit.web.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAccounts();

    public AccountDTO getAccount(Long id);

    public ResponseEntity<Object> createAccount(String email);


}
