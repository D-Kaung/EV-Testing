package com.steve.ev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class ChargingTransaction {

    @Id
    private String id;

    @NotBlank(message = "Connector is cannot be null.")
    private String chargerId;

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private double energyConsumed;
    private double cost;
    @NotBlank(message = "Status cannot be null.")
    private String status;
    private LocalDateTime updateAt;

    public ChargingTransaction(String id, LocalDateTime updateAt, String status, double cost,
                               double energyConsumed,
                               LocalDateTime stopTime, LocalDateTime startTime, String chargerId) {
        this.id = id;
        this.updateAt = updateAt;
        this.status = status;
        this.cost = cost;
        this.energyConsumed = energyConsumed;
        this.stopTime = stopTime;
        this.startTime = startTime;
        this.chargerId = chargerId;
    }

    public ChargingTransaction() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public  String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public  String getChargerId() {
        return chargerId;
    }

    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }
}
