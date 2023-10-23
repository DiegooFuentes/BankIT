package com.bankit.web.dtos;

import com.bankit.web.models.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {

    private Long id;

    private String number;

    private LocalDateTime creationDate;

    private double balance;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
    }
}
