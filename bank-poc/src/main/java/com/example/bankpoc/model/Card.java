package com.example.bankpoc.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String pinHash;
    private String ownerName;
    private BigDecimal balance;

    public Card() {}

    public Card(Long id, String cardNumber, String pinHash, String ownerName, BigDecimal balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pinHash = pinHash;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getPinHash() { return pinHash; }
    public void setPinHash(String pinHash) { this.pinHash = pinHash; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
