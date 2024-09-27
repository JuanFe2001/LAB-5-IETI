package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        // Arrange
        double latitude = 37.8267;
        double longitude = -122.4233;


        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherApiResponse.Main mockMain = new WeatherApiResponse.Main();
        mockMain.setTemperature(25.0);
        mockMain.setHumidity(80.0);
        mockResponse.setMain(mockMain);


        String expectedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=54a8872a2666cbc4fc4e5529ebe16ac1";


        when(restTemplate.getForObject(eq(expectedUrl), eq(WeatherApiResponse.class))).thenReturn(mockResponse);


        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);


        assertNotNull(result);


        assertEquals(25.0, result.getTemperature(), 0.01);
        assertEquals(80.0, result.getHumidity(), 0.01);


        verify(restTemplate, times(1)).getForObject(eq(expectedUrl), eq(WeatherApiResponse.class));
    }
}
