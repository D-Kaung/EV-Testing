package com.steve.ev.service;

import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.model.ChargingPointResponse;
import com.steve.ev.repository.ChargingPointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingPointService {

    private final ChargingPointRepo chargingPointRepo;

    @Autowired
    public ChargingPointService(ChargingPointRepo chargingPointRepo) {
        this.chargingPointRepo = chargingPointRepo;
    }

    public List<ChargingPointResponse> getStationsStatuses(){
        List<ChargingPoint> chargePoints = chargingPointRepo.findAll();
        return chargePoints.stream()
                .map( station -> new ChargingPointResponse(station.getId(), station.getStatus()))
                .collect(Collectors.toList());
    }
}
