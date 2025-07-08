package com.steve.ev.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "ChargerId is required.")
    private Long id;

    @NotBlank(message = "Vendor is required.")
    private String vendor;

    @NotBlank(message = "Model is required.")
    private String model;

    @NotBlank(message = "Status must not blank.")
    private String status; //From enum class (Available, UnAvailable, etc)

    @NotNull(message = "Last heartbeat time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastHeartbeat;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registeredAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastStatusNotification;

    public ChargingStation(Long id, LocalDateTime lastStatusNotification,
                           LocalDateTime registeredAt, LocalDateTime lastHeartbeat,
                           String status, String model, String vendor) {
        this.id = id;
        this.lastStatusNotification = lastStatusNotification;
        this.registeredAt = registeredAt;
        this.lastHeartbeat = lastHeartbeat;
        this.status = status;
        this.model = model;
        this.vendor = vendor;
    }

    public ChargingStation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastStatusNotification() {
        return lastStatusNotification;
    }

    public void setLastStatusNotification(LocalDateTime lastStatusNotification) {
        this.lastStatusNotification = lastStatusNotification;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public @NotBlank(message = "Status must not blank.") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "Status must not blank.") String status) {
        this.status = status;
    }

    public  LocalDateTime getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat( LocalDateTime lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public  String getModel() {
        return model;
    }

    public void setModel( String model) {
        this.model = model;
    }

    public  String getVendor() {
        return vendor;
    }

    public void setVendor( String vendor) {
        this.vendor = vendor;
    }
}
