package com.steve.ev.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve.ev.Entity.ChargingStation;
import com.steve.ev.Exception.InvalidInputException;
import com.steve.ev.Model.ServerResponse;
import com.steve.ev.Repository.ChargingStationRepo;
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
