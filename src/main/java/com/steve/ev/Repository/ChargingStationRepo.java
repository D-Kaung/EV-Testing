package com.steve.ev.Repository;

import com.steve.ev.Entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargingStationRepo extends JpaRepository<ChargingStation ,String> {

     Optional<ChargingStation> findById(Long chargerId);
}
