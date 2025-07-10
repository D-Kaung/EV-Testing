package com.steve.ev.controller;

import com.steve.ev.model.ChargingPointResponse;
import com.steve.ev.service.ChargingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChargingPointController {

    private final ChargingPointService chargingPointService;

    @Autowired
    public ChargingPointController(ChargingPointService chargingPointService) {
        this.chargingPointService = chargingPointService;
    }

    @GetMapping("/internal/stations")
    public List<ChargingPointResponse> getAllRegisteredStations() {
         return chargingPointService.getStationsStatuses();
    }
}