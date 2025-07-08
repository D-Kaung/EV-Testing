package com.steve.ev.Contoller;

import com.steve.ev.Model.ChargingStationResponse;
import com.steve.ev.Service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    @Autowired
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping("/internal/stations")
    public List<ChargingStationResponse> getAllRegisteredStations() {
         return chargingStationService.getStationsStatuses();
    }
}
