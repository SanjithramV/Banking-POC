package com.example.bankpoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.bankpoc.dto.TxRequest;
import com.example.bankpoc.dto.TxResponse;

@Controller
@RequestMapping
public class System1Controller {

    // Accepts transaction requests and routes to /process if card starts with '4'
    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<TxResponse> transaction(@RequestBody TxRequest req) {
        if (req.getCardNumber() == null || req.getPin() == null || req.getAmount() == null || req.getType() == null) {
            return ResponseEntity.badRequest().body(new TxResponse(false, "Missing fields")); 
        }
        if (req.getAmount().doubleValue() <= 0) {
            return ResponseEntity.badRequest().body(new TxResponse(false, "Amount must be > 0")); 
        }
        if (!("withdraw".equalsIgnoreCase(req.getType()) || "topup".equalsIgnoreCase(req.getType()))) {
            return ResponseEntity.badRequest().body(new TxResponse(false, "Invalid type")); 
        }

        if (!req.getCardNumber().startsWith("4")) {
            return ResponseEntity.ok(new TxResponse(false, "Card range not supported")); 
        }

        // Route to System 2 (same application /process)
        RestTemplate rt = new RestTemplate();
        try {
            ResponseEntity<TxResponse> resp = rt.postForEntity("http://localhost:8080/process", req, TxResponse.class);
            return resp;
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new TxResponse(false, "Processing error: " + e.getMessage()));
        }
    }
}
