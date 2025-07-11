package com.steve.ev.service;

import com.steve.ev.cache.ChargingPointStatusCache;
import com.steve.ev.entity.ChargingPoint;
import com.steve.ev.repository.ChargingPointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class ChargingPointStatusMonitor {

    private final ChargingPointStatusCache chargingPointStatusCache;
    private final ChargingPointRepo chargingPointRepo;

    public ChargingPointStatusMonitor(ChargingPointStatusCache chargingPointStatusCache,
                                      ChargingPointRepo chargingPointRepo) {
        this.chargingPointStatusCache = chargingPointStatusCache;
        this.chargingPointRepo = chargingPointRepo;
    }

    @Scheduled(cron = "0 */2 * * * *") //cron for 5 minutes
    public void surveyStations() {
        System.out.println("Executing scheduler");

        long cutofftime = 2; //in minutes
        LocalDateTime cutOffTime = LocalDateTime.now().minusMinutes(cutofftime);
        ConcurrentMap<String, LocalDateTime> unavailableStations = chargingPointStatusCache.
                getChargersToMarkUnavailable(cutOffTime);
//        log.info("{} charging stations are now unavailable.", unavailableStations.size());

        for (String id : unavailableStations.keySet()) {
            ChargingPoint chargeBox = chargingPointRepo.findById(id).orElse(null);
            if (chargeBox != null) {
                chargeBox.setStatus("Unavailable");
                chargingPointRepo.save(chargeBox);
            }

        }

    }
}
