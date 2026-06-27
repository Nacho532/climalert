package com.utn.climalert.service;

import com.utn.climalert.client.CurrentWeather;
import com.utn.climalert.entity.WeatherRecord;
import com.utn.climalert.notificador.NotificarEmail;
import com.utn.climalert.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final NotificarEmail   notificarEmail;

    public WeatherService(WeatherRepository weatherRepository, NotificarEmail notificarEmail) {
        this.weatherRepository = weatherRepository;
        this.notificarEmail = notificarEmail;
    }

    public void registrarClima(CurrentWeather currentWeather) {
        WeatherRecord record = new WeatherRecord(
                currentWeather.tempC(),
                currentWeather.humidity(),
                LocalDateTime.now()
        );

        weatherRepository.save(record);
        System.out.println("Registro guardado Total en memoria: " + weatherRepository.findAll().size());
    }

    public void analizarUltimoClima() {
        weatherRepository.findLast().ifPresentOrElse(clima -> {
            if (clima.getTemperature() > 35.0 && clima.getHumidity() > 60) {
                System.out.println("ALERTA T: " + clima.getTemperature() + "°C, H: " + clima.getHumidity() + "%");
                notificarEmail.enviarAlerta(clima);
            } else {
                System.out.println("Clima normal. T: " + clima.getTemperature() + "°C, H: " + clima.getHumidity() + "%");
            }

        }, () -> System.out.println("No hay datos para analizar."));
    }
}
