package com.bankit.web.services;

import com.bankit.web.dtos.LoanApplicationDTO;
import com.bankit.web.dtos.LoanDTO;
import com.bankit.web.models.*;
import com.bankit.web.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bankit.web.utils.TransactionUtil.transactionUtil;
import static java.util.stream.Collectors.toList;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final ClientLoanRepository clientLoanRepository;
    private final TransactionRepository transactionRepository;

    public LoanServiceImpl(LoanRepository loanRepository, AccountRepository accountRepository,
                          ClientRepository clientRepository, ClientLoanRepository clientLoanRepository,
                          TransactionRepository transactionRepository) {
        this.loanRepository = loanRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.clientLoanRepository = clientLoanRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> requestLoan(LoanApplicationDTO loanApplicationDTO, String email) {

        Loan loanType = loanRepository.findById(loanApplicationDTO.getLoanId()).orElse(null);
        Client client = clientRepository.findByEmail(email);
        Account account = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());

        //Verificar que los datos sean correctos, es decir no estén vacíos, que el monto no
        // sea 0 o que las cuotas no sean 0.
        if (loanApplicationDTO.getAmount().isNaN() || loanApplicationDTO.getPayments() == 0 ||
                loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getToAccountNumber().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        //Verificar que el préstamo exista
        if (!loanRepository.existsById(loanApplicationDTO.getLoanId())) {
            return new ResponseEntity<>("Loan does not exist", HttpStatus.FORBIDDEN);
        }

        //Verificar que el monto solicitado no exceda el monto máximo del préstamo
        assert loanType != null;
        if (loanApplicationDTO.getAmount() > loanType.getMaxAmount()) {
            return new ResponseEntity<>("Exceed the max amount", HttpStatus.FORBIDDEN);
        }

        //Verifica que la cantidad de cuotas se encuentre entre las disponibles del préstamo
        if(!loanType.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("No installments available", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta de destino exista
        if (!accountRepository.existsAccountByNumber(loanApplicationDTO.getToAccountNumber())) {
            return new ResponseEntity<>("Account does not exist", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta de destino pertenezca al cliente autenticado
        if (!client.getAccounts().contains(account)) {
            return new ResponseEntity<>("Account does not belong to this client",HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments(),
                client, loanType);
        clientLoanRepository.save(clientLoan);

        Transaction transaction = transactionUtil(TransactionType.CREDIT,loanApplicationDTO.getAmount(),
                "Loan approved", LocalDateTime.now(),account);

        transactionRepository.save(transaction);
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(account);

        return new ResponseEntity<>("Loan approved", HttpStatus.CREATED);
    }

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList());

    }
}
