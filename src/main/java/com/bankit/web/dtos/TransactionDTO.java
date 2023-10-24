package com.bankit.web.dtos;

import com.bankit.web.models.TransactionType;
import com.bankit.web.models.Transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private TransactionType type;

    private double amount;

    private String description;

    private LocalDateTime date;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
    }

}
