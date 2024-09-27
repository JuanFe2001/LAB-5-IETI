package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        // Arrange
        double latitude = 37.8267;
        double longitude = -122.4233;
        WeatherReport mockWeatherReport = new WeatherReport();

        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockWeatherReport);


        WeatherReport result = weatherReportController.getWeatherReport(latitude, longitude);


        assertNotNull(result);
        assertEquals(mockWeatherReport, result);
        verify(weatherReportService, times(1)).getWeatherReport(latitude, longitude);
    }
}
