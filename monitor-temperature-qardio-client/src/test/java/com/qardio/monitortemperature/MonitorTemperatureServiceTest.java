package com.qardio.monitortemperature;

import com.qardio.monitortemperature.model.AggregatedTempData;
import com.qardio.monitortemperature.model.AggregatedTempRequestType;
import com.qardio.monitortemperature.model.TemperatureData;
import com.qardio.monitortemperature.resources.MonitorTempClientResource;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MonitorTemperatureServiceTest {

        @Test
        public void saveTempData()
        {
               assertEquals(HttpStatus.OK, MonitorTempClientResource.saveTempData("1234",new TemperatureData(System.currentTimeMillis(),98.9)).getStatusCode());

        }

}



