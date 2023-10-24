package com.bankit;

import com.bankit.web.models.Account;
import com.bankit.web.models.Client;
import com.bankit.web.models.Transaction;
import com.bankit.web.models.TransactionType;
import com.bankit.web.repositories.ClientRepository;
import com.bankit.web.repositories.AccountRepository;

import com.bankit.web.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class BankItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankItApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        return (args) -> {
            // save a couple of customers
            Client client1 = clientRepository.save(new Client("Camila", "Balmaceda", "camilabal1@gmail.com", "hoLa32-"));
            Account account1= accountRepository.save(new Account("VIN001", LocalDateTime.now(),127000.0,client1));
            accountRepository.save(new Account("VIN002", LocalDateTime.now(),99000.0,client1));

            clientRepository.save(new Client("Fabian", "Jackson", "fabiqueza@gmail.com", "wo1lDs12"));

            transactionRepository.save(new Transaction(TransactionType.DEBIT,2000.0,"pepe",LocalDateTime.now(),account1));
            transactionRepository.save(new Transaction(TransactionType.CREDIT,9900.0,"jaja",LocalDateTime.now(),account1));


        };
    }
}
