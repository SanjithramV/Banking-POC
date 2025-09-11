package com.example.bankpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bankpoc.model.TxLog;
import java.util.List;

public interface TxLogRepository extends JpaRepository<TxLog, Long> {
    List<TxLog> findByCardNumberOrderByTimestampDesc(String cardNumber);
}
