package com.bankit.web.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@Entity
@Table(name = "Card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String cardHolder;

    @NonNull
    private CardType type;

    @NonNull
    private CardColor color;

    @NonNull
    private String number;

    @NonNull
    private String cvv;

    @NonNull
    private LocalDateTime thruDate;

    @NonNull
    private LocalDateTime fromDate;

    //@Getter(AccessLevel.NONE) //equals to JsonIgnore
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
}
