package com.qardio.monitortemperature.dto;

import com.qardio.monitortemperature.model.AggregatedTempData;
import com.qardio.monitortemperature.model.TemperatureData;

import java.util.*;

public class TemperatureDataDto {

    public static Map<String,List<TemperatureData>> mapOfDeviceToData=new HashMap<>(0);
    public static Map<String,TreeMap<String,AggregatedTempData>> mapOfDeviceToDataHourly=new HashMap<>(0);
    public static Map<String,TreeMap<String,AggregatedTempData>> mapOfDeviceToDataDaily=new HashMap<>(0);
}
