package com.qardio.monitortemperature.resources;


import com.qardio.monitortemperature.model.AggregatedTempData;
import com.qardio.monitortemperature.model.TemperatureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class MonitorTempClientResource {

    @Autowired
    private static RestTemplate restTemplate;

    public static ResponseEntity saveTempData(String deviceId,TemperatureData tempData) {

        HttpHeaders header = new HttpHeaders();
        header.set("DEVICE-ID",deviceId);

        HttpEntity<Object> requestEntity = new HttpEntity<>(tempData, header);

        ResponseEntity<String> response=restTemplate.postForEntity("http://localhost:8082/temperatureData/saveTempData",requestEntity,String.class);

        return response;
    }
    public static ResponseEntity saveTempDataBatch(String deviceId,List<TemperatureData> tempDataList) {

        HttpHeaders header = new HttpHeaders();
        header.set("DEVICE-ID",deviceId);


        HttpEntity<Object> requestEntity = new HttpEntity<>(tempDataList, header);

        ResponseEntity<String> response=restTemplate.postForEntity("http://localhost:8082/temperatureData/saveTempDataBatch",requestEntity,String.class);

        if(response.getStatusCode().is2xxSuccessful())
        {
            System.out.println("Success");

        }
        return response;
    }


    public static AggregatedTempData getAggregatedTempData(String deviceId,String requestType) throws URISyntaxException {

        HttpHeaders header = new HttpHeaders();
        header.set("DEVICE-ID",deviceId);

        HttpEntity<String> requestEntity = new HttpEntity<>( header);
        final String baseUrl = "http://localhost:8082/temperatureData/getAggregatedTempData?requestType="+requestType;
        URI uri = new URI(baseUrl);
        ResponseEntity<AggregatedTempData> response=restTemplate.exchange(uri, HttpMethod.GET,requestEntity,new ParameterizedTypeReference<AggregatedTempData>(){});
        System.out.println(response.getStatusCode());
        return response.getBody();
    }

}
