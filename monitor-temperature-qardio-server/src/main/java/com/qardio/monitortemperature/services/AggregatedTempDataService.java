package com.qardio.monitortemperature.services;

import com.qardio.monitortemperature.dto.TemperatureDataDto;
import com.qardio.monitortemperature.model.AggregatedTempData;
import com.qardio.monitortemperature.model.TemperatureData;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AggregatedTempDataService {

    public AggregatedTempData getAggregatedDataPerHour(String deviceId)
    {
        Date dt=  new Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd HH"); // Put in Utility function

        if( TemperatureDataDto.mapOfDeviceToDataHourly.containsKey(deviceId))
            return TemperatureDataDto.mapOfDeviceToDataHourly.get(deviceId).get(fmt.format(dt));
        else
            return null;
    }
    public AggregatedTempData getAggregatedDataPerDay(String deviceId)
    {
        Date dt=  new Date(System.currentTimeMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd"); // Put in Utility function

        if( TemperatureDataDto.mapOfDeviceToDataDaily.containsKey(deviceId))
            return TemperatureDataDto.mapOfDeviceToDataDaily.get(deviceId).get(fmt.format(dt));
        else
            return null;
    }

    public void saveTempData(String deviceId,TemperatureData data)
    {
        if(!TemperatureDataDto.mapOfDeviceToData.containsKey(deviceId)) {
            TemperatureDataDto.mapOfDeviceToData.put(deviceId,new ArrayList<>());
        }
        TemperatureDataDto.mapOfDeviceToData.get(deviceId).add(data);
        aggregateDataHourly(deviceId);
        aggregateDataDaily(deviceId);

    }
    public void saveTempDataBatch( String deviceId,List<TemperatureData> data)
    {
        if(!TemperatureDataDto.mapOfDeviceToData.containsKey(deviceId)) {
            TemperatureDataDto.mapOfDeviceToData.put(deviceId,new ArrayList<>());
        }
        TemperatureDataDto.mapOfDeviceToData.get(deviceId).addAll(data);
        aggregateDataHourly(deviceId);
        aggregateDataDaily(deviceId);

    }
    public void aggregateDataHourly(String deviceId){

        if(!TemperatureDataDto.mapOfDeviceToDataHourly.containsKey(deviceId))
        {
            TemperatureDataDto.mapOfDeviceToDataHourly.put(deviceId,new TreeMap<>());
        }

        //write logic to aggregate data per hr

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd HH");
        Map<String,List<Double>> tempReadingPerHour=new HashMap<>();
        for(TemperatureData data:TemperatureDataDto.mapOfDeviceToData.get(deviceId))
        {
            Date d = new Date(data.getTimestamp());
            if(!tempReadingPerHour.containsKey(fmt.format(d)))
            {
                tempReadingPerHour.put(fmt.format(d),new ArrayList<>());
            }
            tempReadingPerHour.get(fmt.format(d)).add(data.getTempReading());

        }
        for(Map.Entry<String,List<Double>> mapDaily:tempReadingPerHour.entrySet())
        {
            AggregatedTempData agg=new AggregatedTempData();
            agg.setMaxTemp(mapDaily.getValue().stream().max(Double::compare).get());
            agg.setMinTemp(mapDaily.getValue().stream().min(Double::compare).get());
            agg.setAvgTemp(mapDaily.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN));
            TemperatureDataDto.mapOfDeviceToDataHourly.get(deviceId).put(mapDaily.getKey(),agg);
        }

    }
    public void aggregateDataDaily(String deviceId){
        if(!TemperatureDataDto.mapOfDeviceToDataDaily.containsKey(deviceId))
        {
            TemperatureDataDto.mapOfDeviceToDataDaily.put(deviceId,new TreeMap<>());
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Map<String,List<Double>> tempReadingPerDay=new HashMap<>();
        for(TemperatureData data:TemperatureDataDto.mapOfDeviceToData.get(deviceId))
        {
            Date d = new Date(data.getTimestamp());
            if(!tempReadingPerDay.containsKey(fmt.format(d)))
            {
                tempReadingPerDay.put(fmt.format(d),new ArrayList<>());
            }
            tempReadingPerDay.get(fmt.format(d)).add(data.getTempReading());

        }
        for(Map.Entry<String,List<Double>> mapDaily:tempReadingPerDay.entrySet())
        {
           AggregatedTempData agg=new AggregatedTempData();
           agg.setMaxTemp(mapDaily.getValue().stream().max(Double::compare).get());
           agg.setMinTemp(mapDaily.getValue().stream().min(Double::compare).get());
           agg.setAvgTemp(mapDaily.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN));
           TemperatureDataDto.mapOfDeviceToDataDaily.get(deviceId).put(mapDaily.getKey(),agg);
        }

    }
}
