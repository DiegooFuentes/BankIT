package com.bankit.web.services;

import com.bankit.web.dtos.CardDTO;
import com.bankit.web.models.Card;
import com.bankit.web.models.CardColor;
import com.bankit.web.models.CardType;
import com.bankit.web.models.Client;
import com.bankit.web.repositories.CardRepository;
import com.bankit.web.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bankit.web.models.CardType.CREDIT;
import static com.bankit.web.models.CardType.DEBIT;
import static com.bankit.web.utils.CardUtil.createCardUtil;
import static java.util.stream.Collectors.toList;

@Service
public class CardServiceImpl implements CardService {

    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;

    public CardServiceImpl(ClientRepository clientRepository, CardRepository cardRepository) {
        this.clientRepository = clientRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<Object> createCard(CardColor color, CardType cardType, String email) {
        Client client = clientRepository.findByEmail(email);
        List<Card> debitCards = cardRepository.findCardByTypeAndClient(DEBIT, client);
        List<Card> creditCards = cardRepository.findCardByTypeAndClient(CREDIT, client);

        if (cardType == DEBIT && debitCards.size() <= 2) {
            Card card = createCardUtil(DEBIT, color, client);
            cardRepository.save(card);
            return new ResponseEntity<>("Debit card created", HttpStatus.CREATED);
        } else if (cardType == CREDIT && creditCards.size() <= 2) {
            Card card = createCardUtil(CREDIT, color, client);
            cardRepository.save(card);
            return new ResponseEntity<>("Credit card created", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Max amount of cards reached", HttpStatus.FORBIDDEN);

        }

    }

    @Override
    public List<CardDTO> getCurrentCards(String email) {
        Client client = clientRepository.findByEmail(email);
        return client.getCards().stream().map(CardDTO::new).collect(toList());
    }
}
