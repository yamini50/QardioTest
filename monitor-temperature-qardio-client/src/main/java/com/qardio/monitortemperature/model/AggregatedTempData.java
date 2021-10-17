package com.qardio.monitortemperature.model;

public class AggregatedTempData {

    AggregatedTempRequestType tempType;
    Double avgTemp;
    Double maxTemp;
    Double MinTemp;


    public AggregatedTempData(Double avgTemp, Double maxTemp, Double minTemp) {
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
        MinTemp = minTemp;
    }

    public AggregatedTempRequestType getTempType() {
        return tempType;
    }

    public void setTempType(AggregatedTempRequestType tempType) {
        this.tempType = tempType;
    }
}
