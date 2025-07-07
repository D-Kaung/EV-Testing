package com.steve.ev.Dto;

import java.time.LocalDateTime;

public class TransactionDto {

    private String idTag;
    private int connectorId;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private int meterStart;
    private int meterStop;

    public TransactionDto(String idTag, int connectorId, LocalDateTime startTime,
                          LocalDateTime stopTime, int meterStart, int meterStop) {
        this.idTag = idTag;
        this.connectorId = connectorId;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.meterStart = meterStart;
        this.meterStop = meterStop;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    public int getMeterStart() {
        return meterStart;
    }

    public void setMeterStart(int meterStart) {
        this.meterStart = meterStart;
    }

    public int getMeterStop() {
        return meterStop;
    }

    public void setMeterStop(int meterStop) {
        this.meterStop = meterStop;
    }
}
