package com.example.bankpoc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bankpoc.model.Card;
import com.example.bankpoc.repository.CardRepository;
import com.example.bankpoc.util.SecurityUtil;

import java.math.BigDecimal;

@SpringBootApplication
public class BankPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankPocApplication.class, args);
    }

    // Preload some cards
    @Bean
    CommandLineRunner init(CardRepository cardRepository) {
        return args -> {
            cardRepository.deleteAll();
            // Visa-like cards starting with 4
            Card c1 = new Card(null, "4123456789012345", SecurityUtil.sha256("1234"), "Alice", new java.math.BigDecimal("1000.00"));
            Card c2 = new Card(null, "4123456789012346", SecurityUtil.sha256("4321"), "Bob", new java.math.BigDecimal("50.00"));
            // Non-supported card (starts with 5)
            Card c3 = new Card(null, "5123456789012345", SecurityUtil.sha256("0000"), "Eve", new java.math.BigDecimal("500.00"));
            cardRepository.save(c1);
            cardRepository.save(c2);
            cardRepository.save(c3);
        };
    }
}
