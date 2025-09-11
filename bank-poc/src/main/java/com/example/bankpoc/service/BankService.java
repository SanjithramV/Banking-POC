package com.example.bankpoc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankpoc.model.Card;
import com.example.bankpoc.model.TxLog;
import com.example.bankpoc.repository.CardRepository;
import com.example.bankpoc.repository.TxLogRepository;
import com.example.bankpoc.util.SecurityUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class BankService {
    private final CardRepository cardRepository;
    private final TxLogRepository txLogRepository;

    public BankService(CardRepository cardRepository, TxLogRepository txLogRepository) {
        this.cardRepository = cardRepository;
        this.txLogRepository = txLogRepository;
    }

    public Optional<Card> findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Transactional
    public TxLog process(String cardNumber, String pin, BigDecimal amount, String type) {
        TxLog log = new TxLog();
        log.setCardNumber(cardNumber);
        log.setAmount(amount);
        log.setType(type);
        log.setTimestamp(LocalDateTime.now());

        Optional<Card> oc = cardRepository.findByCardNumber(cardNumber);
        if (!oc.isPresent()) {
            log.setStatus("DECLINED");
            log.setReason("Invalid card");
            txLogRepository.save(log);
            return log;
        }
        Card card = oc.get();
        String hashed = SecurityUtil.sha256(pin);
        if (!hashed.equals(card.getPinHash())) {
            log.setStatus("DECLINED");
            log.setReason("Invalid PIN");
            txLogRepository.save(log);
            return log;
        }

        if ("withdraw".equalsIgnoreCase(type)) {
            if (card.getBalance().compareTo(amount) < 0) {
                log.setStatus("DECLINED");
                log.setReason("Insufficient balance");
            } else {
                card.setBalance(card.getBalance().subtract(amount));
                cardRepository.save(card);
                log.setStatus("SUCCESS");
                log.setReason("Withdrawn");
            }
        } else if ("topup".equalsIgnoreCase(type)) {
            card.setBalance(card.getBalance().add(amount));
            cardRepository.save(card);
            log.setStatus("SUCCESS");
            log.setReason("Top-up");
        } else {
            log.setStatus("DECLINED");
            log.setReason("Invalid type");
        }

        txLogRepository.save(log);
        return log;
    }

    public List<TxLog> allTransactions() {
        return txLogRepository.findAll();
    }

    public List<TxLog> findByCard(String cardNumber) {
        return txLogRepository.findByCardNumberOrderByTimestampDesc(cardNumber);
    }
}
