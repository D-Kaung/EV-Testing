package com.steve.ev.controller;

import com.steve.ev.model.TransactionResponse;
import com.steve.ev.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChargingTransactionController {

    private final TransactionService transactionService;

    @Autowired
    public ChargingTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/internal/transaction")
    @ResponseBody
    public List<TransactionResponse> getAllTransactions(
            @RequestParam("startTime")LocalDateTime startTime,
            @RequestParam("stopTime")LocalDateTime stopTime
            ) {
        return transactionService.getTransactions(startTime, stopTime);
    }
}