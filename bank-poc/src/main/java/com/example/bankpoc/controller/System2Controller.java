package com.example.bankpoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.bankpoc.dto.TxRequest;
import com.example.bankpoc.dto.TxResponse;
import com.example.bankpoc.model.TxLog;
import com.example.bankpoc.service.BankService;

@Controller
@RequestMapping
public class System2Controller {
    private final BankService bankService;

    public System2Controller(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/process")
    @ResponseBody
    public ResponseEntity<TxResponse> process(@RequestBody TxRequest req) {
        TxLog log = bankService.process(req.getCardNumber(), req.getPin(), req.getAmount(), req.getType());
        if ("SUCCESS".equalsIgnoreCase(log.getStatus())) {
            return ResponseEntity.ok(new TxResponse(true, "Success: " + log.getReason()));
        } else {
            return ResponseEntity.ok(new TxResponse(false, log.getReason()));
        }
    }
}
