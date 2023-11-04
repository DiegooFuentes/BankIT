package com.bankit.web.services;

import com.bankit.web.dtos.LoanApplicationDTO;
import com.bankit.web.dtos.LoanDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanService {

    public ResponseEntity<Object> requestLoan(LoanApplicationDTO loanApplicationDTO, String email);

    public List<LoanDTO> getLoans();
}
