package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.repository.ChargingPointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HeartbeatHandler implements ActionHandler{

    private final ChargingPointRepo chargingPointRepo;

    @Autowired
    public HeartbeatHandler(ChargingPointRepo chargingPointRepo) {
        this.chargingPointRepo = chargingPointRepo;
    }

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {
        ChargingPoint charger = chargingPointRepo.findById(chargerId).orElse(null);
        if (charger != null) {
            charger.setLastHeartbeat(LocalDateTime.now());
            charger.setStatus("Available");
            chargingPointRepo.save(charger);
            return new ServerResponse("Accepted");
        } else {
           throw new InvalidInputException("Charger id not match");
        }
    }
}
