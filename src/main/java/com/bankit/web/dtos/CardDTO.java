package com.bankit.web.dtos;

import com.bankit.web.models.Card;
import com.bankit.web.models.CardColor;
import com.bankit.web.models.CardType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardDTO {

    private Long id;

    private String cardHolder;

    private CardType type;

    private CardColor color;

    private String number;

    private String cvv;

    private LocalDateTime thruDate;

    private LocalDateTime fromDate;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
    }
}
