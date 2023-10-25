package com.bankit.web.dtos;

import com.bankit.web.models.ClientLoan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLoanDTO {

    private Long id;

    private Long loanId;

    private String name;

    private Double amount;

    private Integer payments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }
}
