package com.steve.ev.repository;

import com.steve.ev.entity.ChargingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargingPointRepo extends JpaRepository<ChargingPoint, String> {

     Optional<ChargingPoint> findById(String chargerId);
}
