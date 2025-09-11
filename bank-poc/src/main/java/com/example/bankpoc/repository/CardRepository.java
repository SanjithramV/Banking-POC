package com.example.bankpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bankpoc.model.Card;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
}
