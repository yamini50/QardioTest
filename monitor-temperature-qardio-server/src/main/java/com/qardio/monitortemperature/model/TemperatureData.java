package com.qardio.monitortemperature.model;

import java.time.LocalDateTime;

public class TemperatureData {

    Long timestamp;
    Double tempReading;

    public TemperatureData(Long timestamp, Double tempReading) {
        this.timestamp = timestamp;
        this.tempReading = tempReading;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTempReading() {
        return tempReading;
    }

    public void setTempReading(Double tempReading) {
        this.tempReading = tempReading;
    }



}
