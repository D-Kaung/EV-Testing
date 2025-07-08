package com.steve.ev.cache;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ChargingStationStatusCache {

    private final ConcurrentMap<Long, LocalDateTime> chargerStatusMap = new ConcurrentHashMap<>();

    public void updateStatus(Long chargerId, LocalDateTime lastStatusUpdateTime) {
        chargerStatusMap.put(chargerId, lastStatusUpdateTime);
    }

    public LocalDateTime getLastStatusUpdateTime(Long chargerId) {
        return chargerStatusMap.get(chargerId);
    }

    public void removeCharger(Long chargerId) {
        chargerStatusMap.remove(chargerId);
    }

    public ConcurrentMap<Long, LocalDateTime> getChargersToMarkUnavailable(LocalDateTime cutoffTime) {
        ConcurrentMap<Long, LocalDateTime> chargersToUpdate = new ConcurrentHashMap<>();
        chargerStatusMap.forEach((chargerId, lastStatusUpdateTime) -> {
            if (lastStatusUpdateTime.isBefore(cutoffTime)) {
                chargersToUpdate.put(chargerId, lastStatusUpdateTime);
            }
        });
        return chargersToUpdate;
    }
}
