package com.example.bankpoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.bankpoc.service.BankService;
import com.example.bankpoc.model.Card;

@Controller
public class UIController {
    private final BankService bankService;

    public UIController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("txs", bankService.allTransactions());
        return "admin";
    }

    @GetMapping("/customer/login")
    public String customerLogin() { return "customer_login"; }

    @PostMapping("/customer/login")
    public String customerDoLogin(@RequestParam String cardNumber, @RequestParam String pin, Model model) {
        var oc = bankService.findByCardNumber(cardNumber);
        if (oc.isEmpty()) {
            model.addAttribute("error", "Invalid card");
            return "customer_login";
        }
        Card c = oc.get();
        // validate pin
        String hashed = com.example.bankpoc.util.SecurityUtil.sha256(pin);
        if (!hashed.equals(c.getPinHash())) {
            model.addAttribute("error", "Invalid PIN");
            return "customer_login";
        }
        model.addAttribute("card", c);
        model.addAttribute("txs", bankService.findByCard(cardNumber));
        return "customer_dashboard";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard() { return "customer_dashboard"; }
}
