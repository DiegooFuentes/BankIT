package com.bankit;

import com.bankit.web.models.*;
import com.bankit.web.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class BankItApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankItApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
        return (args) -> {
            // save a couple of customers

            Client client1 = clientRepository.save(new Client("Camila", "Balmaceda", "camilabal1@gmail.com", passwordEncoder.encode("1")));
            Account account1= accountRepository.save(new Account("VIN001", LocalDateTime.now(),127000.0,client1));
            accountRepository.save(new Account("VIN002", LocalDateTime.now(),99000.0,client1));

            clientRepository.save(new Client("Fabian", "Jackson", "fabiqueza@gmail.com", passwordEncoder.encode("wo1lDs12")));

            transactionRepository.save(new Transaction(TransactionType.DEBIT,2000.0,"pepe",LocalDateTime.now(),account1));
            transactionRepository.save(new Transaction(TransactionType.CREDIT,9900.0,"jaja",LocalDateTime.now(),account1));

            loanRepository.save(new Loan("Mortgage", 500000.0, List.of(12, 24, 36, 48, 60)));
            loanRepository.save(new Loan("Personal", 100000.0, List.of(6, 12, 24)));
            Loan loan1 = loanRepository.save(new Loan("Car", 300000.0, List.of(6, 12, 24, 36)));

            clientLoanRepository.save(new ClientLoan(100.0, 6, client1, loan1));

            clientLoanRepository.save(new ClientLoan(990.0, 24, client1, loan1));

            cardRepository.save(new Card( client1.getFirstName() + " " + client1.getLastName(),CardType.DEBIT, CardColor.GOLD, "1234 1234 1234 1234", "123", LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1));

            cardRepository.save(new Card( client1.getFirstName() + " " + client1.getLastName(),CardType.CREDIT, CardColor.TITANIUM, "9999 0000 9999 0000", "222", LocalDateTime.now(), LocalDateTime.now().plusYears(3), client1));

        };
    }
}
