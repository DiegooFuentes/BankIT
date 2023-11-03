package com.bankit.web.services;

import org.springframework.http.ResponseEntity;

public interface TransactionService {

    public ResponseEntity<Object> makeTransaction(String fromAccountNumber, String toAccountNumber, Double amount, String description, String email);
}
