package com.bankit.web.dtos;

import com.bankit.web.models.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter
@Setter
public class AccountDTO {

    private Long id;

    private String number;

    private LocalDateTime creationDate;

    private double balance;

    private Set<TransactionDTO> transactions;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(toSet());
    }
}
