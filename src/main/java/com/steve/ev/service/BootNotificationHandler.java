package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve.ev.entity.ChargingStation;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.repository.ChargingStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BootNotificationHandler implements ActionHandler{

    private final ChargingStationRepo chargingStationRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BootNotificationHandler(ChargingStationRepo chargingStationRepo) {
        this.chargingStationRepo = chargingStationRepo;
    }

    @Override
    public ServerResponse handle(Long chargerId, JsonNode payload) {
        String vendor = payload.path("ChargePointVendor").asText(null);
        String model = payload.path("ChargePointModel").asText(null);
        String status = payload.path("status").asText(null);
        LocalDateTime currentTime = LocalDateTime.now();

        if (vendor == null || model == null){
            throw new InvalidInputException("ChargePointVendor and ChargePointModel are required.");
        }
        ChargingStation charger = chargingStationRepo.findById(chargerId).orElseGet(()->
        {
           ChargingStation newCharger = new ChargingStation();
           newCharger.setId(chargerId);
           newCharger.setRegisteredAt(currentTime);
           return newCharger;
        });
        charger.setVendor(vendor);
        charger.setModel(model);
        charger.setLastHeartbeat(currentTime);
        charger.setStatus(status);
        charger.setLastStatusNotification(currentTime);

        chargingStationRepo.save(charger);
        return new ServerResponse("Accepted");
    }
}
