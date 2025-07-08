package com.steve.ev.service;

import com.steve.ev.model.TransactionResponse;
import com.steve.ev.repository.ChargingTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final ChargingTransactionRepo chargingTransactionRepo;

    @Autowired
    public TransactionService(ChargingTransactionRepo chargingTransactionRepo) {
        this.chargingTransactionRepo = chargingTransactionRepo;
    }

    public List<TransactionResponse> getTransactions(LocalDateTime startTime, LocalDateTime stopTime) {
        List<TransactionResponse> responses = new ArrayList<>();

        chargingTransactionRepo.findByStartTimeBetween(startTime, stopTime)
                .forEach((transaction) -> responses.add(new TransactionResponse(
                        transaction.getId(),  transaction.getStatus()
                )));
        return responses;
    }
}
