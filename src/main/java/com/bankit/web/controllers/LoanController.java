package com.bankit.web.controllers;

import com.bankit.web.dtos.LoanApplicationDTO;
import com.bankit.web.dtos.LoanDTO;
import com.bankit.web.services.LoanServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanServiceImpl loanService;

    public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;

    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> requestLoan(@RequestBody LoanApplicationDTO loanApplicationDTO,
                                          Authentication authentication) {

        return loanService.requestLoan(loanApplicationDTO, authentication.getName());

    }

    @GetMapping("/loans")
    public List<LoanDTO> getLoan () {
        return loanService.getLoans();
    }

}
