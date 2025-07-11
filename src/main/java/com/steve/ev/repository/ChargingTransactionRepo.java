package com.steve.ev.repository;

import com.steve.ev.entity.ChargingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChargingTransactionRepo extends JpaRepository<ChargingTransaction , String> {

    List<ChargingTransaction> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime stopTime);

}
