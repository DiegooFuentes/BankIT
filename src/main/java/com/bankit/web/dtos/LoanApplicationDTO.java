package com.bankit.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplicationDTO {

    private Long loanId;

    private Double amount;

    private Integer payments;

    private String toAccountNumber;

    public LoanApplicationDTO(Long loanId, Double amount, Integer payments, String toAccountNumber) {
        this.loanId = loanId;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    }
}
