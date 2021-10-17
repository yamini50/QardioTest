package com.qardio.monitortemperature.model;

import org.springframework.stereotype.Component;

public class AggregatedTempData {

    Double avgTemp;

    public Double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(Double minTemp) {
        MinTemp = minTemp;
    }

    Double maxTemp;
    Double MinTemp;




    public AggregatedTempData() {

    }

}
