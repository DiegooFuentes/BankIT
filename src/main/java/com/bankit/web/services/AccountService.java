package com.bankit.web.services;

import com.bankit.web.dtos.AccountDTO;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAccounts();

    public AccountDTO getAccount(Long id);

}
