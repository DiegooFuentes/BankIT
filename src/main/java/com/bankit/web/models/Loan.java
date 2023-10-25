package com.bankit.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@Entity
@Table(name = "Loan") //pascal case on table name and camel case on columns
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Double maxAmount;

    @ElementCollection
    @Column(name="payments")
    @NonNull
    private List<Integer> payments = new ArrayList<>();

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    Set<ClientLoan> clientLoans;

    public Loan(@NonNull String name, @NonNull Double maxAmount, @NonNull List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    @JsonIgnore
    public List<Client> getClients() {
        return clientLoans.stream().map(ClientLoan::getClient).collect(toList());
    }
}
