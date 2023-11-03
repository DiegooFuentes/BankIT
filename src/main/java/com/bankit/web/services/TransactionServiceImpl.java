package com.bankit.web.services;

import com.bankit.web.models.Account;
import com.bankit.web.models.Client;
import com.bankit.web.models.Transaction;
import com.bankit.web.models.TransactionType;
import com.bankit.web.repositories.AccountRepository;
import com.bankit.web.repositories.ClientRepository;
import com.bankit.web.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.bankit.web.utils.TransactionUtil.transactionUtil;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> makeTransaction(String fromAccountNumber,
                                                  String toAccountNumber,
                                                  Double amount,
                                                  String description,
                                                  String email) {

        Account destinationAccount = accountRepository.findByNumber(toAccountNumber);
        Client originClient = clientRepository.findByEmail(email);
        Account originAccount = accountRepository.findByNumber(fromAccountNumber);

        //Verificar que los parámetros no estén vacíos
        if (amount == null || amount.isNaN() || description.isEmpty() || fromAccountNumber.isEmpty() || toAccountNumber.isEmpty()) {
            return new ResponseEntity<>("Missing data or invalid data", HttpStatus.FORBIDDEN);
        }

        //Verificar que los números de cuenta no sean iguales
        if (fromAccountNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>("Both accounts have the same number", HttpStatus.FORBIDDEN);
        }

        //Verificar que exista la cuenta de origen
        if (accountRepository.existsAccountByNumber(fromAccountNumber)) {
            return new ResponseEntity<>("Origin account does not exist", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta de origen pertenezca al cliente autenticado
        if (!originClient.getAccounts().contains(originAccount)) {
            return new ResponseEntity<>("Origin account does not belong to the current client.",HttpStatus.FORBIDDEN);
        }

        //Verificar que exista la cuenta de destino
        if (accountRepository.existsAccountByNumber(toAccountNumber)) {
            return new ResponseEntity<>("Destination account does not exist",HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta de origen tenga el monto disponible.
        if (originAccount.getBalance() < amount) {
            return new ResponseEntity<>("Insufficient balance",HttpStatus.FORBIDDEN);
        }

        Transaction transactionOrigin = transactionUtil(TransactionType.DEBIT,amount,description,LocalDateTime.now(),originAccount);
        transactionRepository.save(transactionOrigin);
        originAccount.setBalance(originAccount.getBalance() - amount);
        accountRepository.save(originAccount);

        Transaction transactionDestination = transactionUtil(TransactionType.CREDIT,amount,description,LocalDateTime.now(),destinationAccount);
        transactionRepository.save(transactionDestination);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
        accountRepository.save(destinationAccount);

        return new ResponseEntity<>("Transaction done",HttpStatus.CREATED);

    }
}
