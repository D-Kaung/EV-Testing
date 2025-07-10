package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.repository.ChargingPointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BootNotificationHandler implements ActionHandler{

    private final ChargingPointRepo chargingPointRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BootNotificationHandler(ChargingPointRepo chargingPointRepo) {
        this.chargingPointRepo = chargingPointRepo;
    }

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {
        String vendor = payload.path("ChargePointVendor").asText(null);
        String model = payload.path("ChargePointModel").asText(null);
        String status = payload.path("status").asText(null);
        LocalDateTime currentTime = LocalDateTime.now();

        if (vendor == null || model == null){
            throw new InvalidInputException("ChargePointVendor and ChargePointModel are required.");
        }
        ChargingPoint charger = chargingPointRepo.findById(chargerId).orElseGet(()->
        {
           ChargingPoint newCharger = new ChargingPoint();
           newCharger.setId(chargerId);
           newCharger.setRegisteredAt(currentTime);
           return newCharger;
        });
        charger.setVendor(vendor);
        charger.setModel(model);
        charger.setLastHeartbeat(currentTime);
        charger.setStatus(status);
        charger.setLastStatusNotification(currentTime);

        chargingPointRepo.save(charger);
        return new ServerResponse("Accepted");
    }
}
