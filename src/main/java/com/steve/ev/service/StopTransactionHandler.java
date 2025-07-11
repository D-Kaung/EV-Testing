package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.entity.ChargingTransaction;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.model.TransactionResponse;
import com.steve.ev.repository.ChargingPointRepo;
import com.steve.ev.repository.ChargingTransactionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StopTransactionHandler extends TransactionHandler implements ActionHandler{

    public StopTransactionHandler(ChargingPointRepo chargingPointRepo,
                                  ChargingTransactionRepo chargingTransactionRepo) {
        super(chargingPointRepo, chargingTransactionRepo);
    }

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {

        String transactionId = payload.path("transactionId").asText(null);
        if (transactionId == null || chargerId == null) {
            throw new RuntimeException("TransactionId and chargerId are required");
        }

        String status = payload.path("status").asText(null);
        double energy = parseEnergy(payload);
        double cost = Double.parseDouble(payload.path("cost").asText("0"));
        LocalDateTime timestamp = parseTimestamp(payload);

        ChargingPoint charger = fetchChargingPoint(chargerId);
        ChargingTransaction transaction = fetchChargingTransaction(transactionId);

        charger.setStatus(status);
        transaction.setUpdateAt(timestamp);
        transaction.setEnergyConsumed(energy);
        transaction.setCost(cost);
        transaction.setStatus(status);

        saveChargingEntities(charger, transaction);

        return new TransactionResponse("Accepted", transactionId);
    }
}
