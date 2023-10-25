package com.bankit;

import com.bankit.web.models.*;
import com.bankit.web.repositories.ClientRepository;
import com.bankit.web.repositories.AccountRepository;

import com.bankit.web.repositories.LoanRepository;
import com.bankit.web.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class BankItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankItApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository) {
        return (args) -> {
            // save a couple of customers
            Client client1 = clientRepository.save(new Client("Camila", "Balmaceda", "camilabal1@gmail.com", "hoLa32-"));
            Account account1= accountRepository.save(new Account("VIN001", LocalDateTime.now(),127000.0,client1));
            accountRepository.save(new Account("VIN002", LocalDateTime.now(),99000.0,client1));

            clientRepository.save(new Client("Fabian", "Jackson", "fabiqueza@gmail.com", "wo1lDs12"));

            transactionRepository.save(new Transaction(TransactionType.DEBIT,2000.0,"pepe",LocalDateTime.now(),account1));
            transactionRepository.save(new Transaction(TransactionType.CREDIT,9900.0,"jaja",LocalDateTime.now(),account1));

            loanRepository.save(new Loan("Mortgage", 500000.0, List.of(12, 24, 36, 48, 60)));
            loanRepository.save(new Loan("Personal", 100000.0, List.of(6, 12, 24)));
            loanRepository.save(new Loan("Car", 300000.0, List.of(6, 12, 24, 36)));


        };
    }
}
