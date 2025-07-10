package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.entity.ChargingTransaction;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.model.TransactionResponse;
import com.steve.ev.repository.ChargingPointRepo;
import com.steve.ev.repository.ChargingTransactionRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.time.LocalDateTime;

@Service
public class StartTransactionHandler extends TransactionHandler implements ActionHandler{

    public StartTransactionHandler(ChargingPointRepo chargingPointRepo,
                                   ChargingTransactionRepo chargingTransactionRepo) {
        super(chargingPointRepo, chargingTransactionRepo);
    }

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {
        String status = payload.path("status").asText(null);
        String id = payload.path("id").asText(UUID.randomUUID().toString());
        double energy = parseEnergy(payload);
        LocalDateTime timestamp = parseTimestamp(payload);
        ChargingPoint charger = fetchChargingPoint(chargerId);

        ChargingTransaction transaction = new ChargingTransaction();
        transaction.setId(id);

        if (energy >= 0) {
            transaction.setStartTime(timestamp);
        }
        transaction.setChargerId(chargerId);
        transaction.setEnergyConsumed(energy);
        transaction.setUpdateAt(timestamp);
        transaction.setStatus(status);

        charger.setStatus(status);
        charger.setLastStatusNotification(timestamp);

        saveChargingEntities(charger, transaction);
        return new TransactionResponse("Accepted", id);
    }
}
