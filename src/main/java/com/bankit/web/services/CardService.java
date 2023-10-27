package com.bankit.web.services;

import com.bankit.web.dtos.CardDTO;
import com.bankit.web.models.Card;
import com.bankit.web.models.CardColor;
import com.bankit.web.models.CardType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {

    public ResponseEntity<Object> createCard(CardColor color, CardType cardType, String email);

    public List<CardDTO> getCurrentCards(String email);
}
