package com.example.bankpoc.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TxLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String type;
    private BigDecimal amount;
    private String status;
    private String reason;
    private LocalDateTime timestamp;

    public TxLog() {}

    public TxLog(Long id, String cardNumber, String type, BigDecimal amount, String status, String reason, LocalDateTime timestamp) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public java.math.BigDecimal getAmount() { return amount; }
    public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
