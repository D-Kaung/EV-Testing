package com.steve.ev.repository;

import com.steve.ev.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargingStationRepo extends JpaRepository<ChargingStation ,String> {

     Optional<ChargingStation> findById(Long chargerId);
}
