package com.utn.climalert.scheduler;

import com.utn.climalert.client.CurrentWeather;
import com.utn.climalert.client.WeatherApiClient;
import com.utn.climalert.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    private final WeatherApiClient weatherApiClient;
    private final WeatherService weatherService;

    public WeatherScheduler(WeatherApiClient weatherApiClient, WeatherService weatherService) {
        this.weatherApiClient = weatherApiClient;
        this.weatherService = weatherService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void buscarClima() {
        System.out.println("--- Buscando Clima ---");
        CurrentWeather clima = weatherApiClient.getCurrentWeather();
        if (clima != null) {
            weatherService.registrarClima(clima);
        } else {
            System.out.println("Reintentando en 5 minutos");
        }
    }
    @Scheduled(cron = "0 * * * * *")
    public void analizarClima() {
        System.out.println("--- Analizando Alertas---");
        weatherService.analizarUltimoClima();
    }
}
