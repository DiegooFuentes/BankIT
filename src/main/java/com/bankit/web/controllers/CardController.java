package com.bankit.web.controllers;

import com.bankit.web.dtos.CardDTO;
import com.bankit.web.models.CardColor;
import com.bankit.web.models.CardType;
import com.bankit.web.services.CardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardServiceImpl cardService;

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam(value="cardType") CardType cardType,
                                             @RequestParam(value="cardColor") CardColor color,
                                             Authentication authentication) {

        return cardService.createCard(color, cardType, authentication.getName());
    }

    @GetMapping("/clients/current/cards")
    public List<CardDTO> getCurrentCards(Authentication authentication) {
        return cardService.getCurrentCards(authentication.getName());
    }
}
