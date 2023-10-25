package com.bankit.web.dtos;

import com.bankit.web.models.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter
@Setter
public class ClientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<AccountDTO> accounts;

    private Set<ClientLoanDTO> loans;

    public ClientDTO(Client client) {

        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(toSet());
        this.loans = client.getLoans().stream().map(ClientLoanDTO::new).collect(toSet());
    }
}
