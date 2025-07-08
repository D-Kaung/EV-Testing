package com.steve.ev.Service;

import com.steve.ev.Entity.ChargingStation;
import com.steve.ev.Model.ChargingStationResponse;
import com.steve.ev.Repository.ChargingStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationService {

    private final ChargingStationRepo chargingStationRepo;

    @Autowired
    public ChargingStationService(ChargingStationRepo chargingStationRepo) {
        this.chargingStationRepo = chargingStationRepo;
    }

    public List<ChargingStationResponse> getStationsStatuses(){
        List<ChargingStation> stations = chargingStationRepo.findAll();
        return stations.stream()
                .map( station -> new ChargingStationResponse(station.getId(), station.getStatus()))
                .collect(Collectors.toList());
    }
}
