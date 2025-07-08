package com.steve.ev.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.Entity.ChargingStation;
import com.steve.ev.Exception.InvalidInputException;
import com.steve.ev.Model.ServerResponse;
import com.steve.ev.Repository.ChargingStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HeartbeatHandler implements ActionHandler{

    private final ChargingStationRepo chargingStationRepo;

    @Autowired
    public HeartbeatHandler(ChargingStationRepo chargingStationRepo) {
        this.chargingStationRepo = chargingStationRepo;
    }

    @Override
    public ServerResponse handle(Long chargeId, JsonNode payload) {
        ChargingStation charger = chargingStationRepo.findById(chargeId).orElse(null);
        if (charger != null) {
            charger.setLastHeartbeat(LocalDateTime.now());
            charger.setStatus("Available");
            chargingStationRepo.save(charger);
            return new ServerResponse("Accepted");
        } else {
           throw new InvalidInputException("Charger id not match");
        }
    }
}
