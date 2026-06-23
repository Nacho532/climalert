package com.utn.climalert.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {
    public CurrentWeather getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "0c53280c80104377833162055262306";
        String location = "CABA";
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;

        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            if (response != null && response.current() != null) {
                return response.current();
            }
        } catch (Exception e) {
            System.err.println("Fallo la conexion con la api, motivo: " + e.getMessage());
        }
        return null;
    }
}
