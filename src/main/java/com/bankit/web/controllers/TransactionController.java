package com.bankit.web.controllers;

import com.bankit.web.services.TransactionServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> makeTransaction(@RequestParam("fromAccountNumber") String fromAccountNumber,
                                                  @RequestParam("toAccountNumber") String toAccountNumber,
                                                  @RequestParam("amount") Double amount,
                                                  @RequestParam("description") String description,
                                                  Authentication authentication) {

        return transactionService.makeTransaction(fromAccountNumber, toAccountNumber, amount, description, authentication.getName());


    }
}
