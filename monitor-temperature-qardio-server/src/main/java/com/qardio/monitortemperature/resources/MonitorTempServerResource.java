package com.qardio.monitortemperature.resources;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.qardio.monitortemperature.model.AggregatedTempData;
import com.qardio.monitortemperature.model.AggregatedTempRequestType;
import com.qardio.monitortemperature.model.InputTypeEnum;
import com.qardio.monitortemperature.model.TemperatureData;
import com.qardio.monitortemperature.services.AggregatedTempDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temperatureData")
public class MonitorTempServerResource {

    @Autowired
    AggregatedTempDataService aggregatedTempDataService;


    @PostMapping(path= "/saveTempData", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> saveTempData(@RequestHeader(name = "DEVICE-ID", required = true) String deviceId,
                             @RequestBody TemperatureData tempData) throws JsonProcessingException {

        ResponseEntity response=new ResponseEntity(HttpStatus.CREATED);
        aggregatedTempDataService.saveTempData(deviceId,tempData);

        return  response;
    }

    @PostMapping(path= "/saveTempDataBatch", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> saveTempDataBatch(@RequestHeader(name = "DEVICE-ID", required = true) String deviceId,
                                               @RequestBody List<TemperatureData> tempDataList) throws JsonProcessingException {

        ResponseEntity response=new ResponseEntity(HttpStatus.CREATED);
        aggregatedTempDataService.saveTempDataBatch(deviceId,tempDataList);

        return  response;
    }
    @GetMapping(path= "/getAggregatedTempData", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public AggregatedTempData getAggregatedTempData(@RequestHeader(name = "DEVICE-ID", required = true) String deviceId, @RequestParam(name = "requestType", required = true) String requestType) {

        if(requestType.equals(AggregatedTempRequestType.HOURLY.name()))
        {
            return aggregatedTempDataService.getAggregatedDataPerHour(deviceId);
        }
        else
            return aggregatedTempDataService.getAggregatedDataPerDay(deviceId);
    }
}
