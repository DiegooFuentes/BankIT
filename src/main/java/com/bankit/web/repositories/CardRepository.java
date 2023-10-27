package com.bankit.web.repositories;

import com.bankit.web.models.Card;
import com.bankit.web.models.CardType;
import com.bankit.web.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardByTypeAndClient (CardType cardType, Client client);
}
