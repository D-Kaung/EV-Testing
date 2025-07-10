package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.entity.ChargingTransaction;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.repository.ChargingPointRepo;
import com.steve.ev.repository.ChargingTransactionRepo;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Service
public class TransactionHandler {

    protected final ChargingPointRepo chargingPointRepo;
    protected final ChargingTransactionRepo chargingTransactionRepo;

    public TransactionHandler(ChargingPointRepo chargingPointRepo,
                              ChargingTransactionRepo chargingTransactionRepo) {
        this.chargingPointRepo = chargingPointRepo;
        this.chargingTransactionRepo = chargingTransactionRepo;
    }

    //common method to parse and validate timestamp
    public LocalDateTime parseTimestamp(JsonNode payload) {
        try {
            return LocalDateTime.parse(payload.path("timestamp").asText(null));
        }catch (DateTimeException exception) {
            throw new InvalidInputException("Something wrong in Input DateTime");
        }
    }

    //common method to parse and validate energy reading
    public double parseEnergy(JsonNode payload) {
        try {
            return Double.parseDouble(payload.path("meterReading").asText(null));
        }catch (NumberFormatException exception) {
            throw new InvalidInputException("Invalid meter value input");
        }
    }

    //common method to fetch charger from the chargingPoint(chargeBox)
    public ChargingPoint fetchChargingPoint(String chargerId) {
        ChargingPoint charger = chargingPointRepo.findById(chargerId).orElse(null);
        if (charger == null) {
            throw new InvalidInputException("Charger cannot find");
        }
        return charger;
    }

    //common method to fetch transaction from the transaction entity
    public ChargingTransaction fetchChargingTransaction(String transactionId) {
        return chargingTransactionRepo.findById(transactionId).orElseThrow(() ->
                new InvalidInputException("Transaction id not found"));
    }

    //common method to save
    public void saveChargingEntities(ChargingPoint charger, ChargingTransaction transaction) {
        chargingPointRepo.save(charger);
        chargingTransactionRepo.save(transaction);
    }
}
