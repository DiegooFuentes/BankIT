package com.bankit.web.dtos;

import com.bankit.web.models.Loan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoanDTO {

    private Long id;

    private String name;

    private double maxAmount;

    private List<Integer> payments;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }
}
