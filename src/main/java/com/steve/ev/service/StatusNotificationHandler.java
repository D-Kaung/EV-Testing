package com.steve.ev.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.exception.InvalidInputException;
import com.steve.ev.model.ServerResponse;
import com.steve.ev.repository.ChargingPointRepo;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@Service
public class StatusNotificationHandler implements ActionHandler{

    private final ChargingPointRepo chargingPointRepo;

    public StatusNotificationHandler(ChargingPointRepo chargingPointRepo) {
        this.chargingPointRepo = chargingPointRepo;
    }

    @Override
    public ServerResponse handle(String chargerId, JsonNode payload) {
        String status = payload.path("status").asText(null);
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(payload.path("timestamp").asText(null));
        }catch (DateTimeException dateTimeException){
            throw new InvalidInputException("timestamp is not in proper format. Provide ISO 8601 combined date and time representation");
        }
        ChargingPoint charger = chargingPointRepo.findById(chargerId).orElse(null);
        if (charger != null){
            charger.setStatus(status);
            charger.setLastStatusNotification(localDateTime);
            chargingPointRepo.save(charger);
            return new ServerResponse("Accepted");
        }else {
            throw new InvalidInputException("Charger Id does not exist");
        }
    }
}
