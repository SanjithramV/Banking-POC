package com.example.bankpoc.dto;

import java.math.BigDecimal;

public class TxRequest {
    private String cardNumber;
    private String pin;
    private BigDecimal amount;
    private String type;

    public TxRequest() {}

    // getters and setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public java.math.BigDecimal getAmount() { return amount; }
    public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
