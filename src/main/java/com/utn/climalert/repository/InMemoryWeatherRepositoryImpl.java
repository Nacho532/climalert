package com.utn.climalert.repository;

import com.utn.climalert.entity.WeatherRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryWeatherRepositoryImpl implements WeatherRepository{

    private final List<WeatherRecord> dbWeather = new ArrayList<>();
    private Long secuenciaId = 1L;

    @Override
    public WeatherRecord save(WeatherRecord record) {
        if (record.getId() == null){
            record.setId(secuenciaId++);
            dbWeather.add(record);
            return record;
        }
        dbWeather.removeIf(w->w.getId().equals(record.getId()));
        dbWeather.add(record);
        return record;
    }


    @Override
    public List<WeatherRecord> findAll() {
        return new ArrayList<>(dbWeather);
    }

    @Override
    public Optional<WeatherRecord> findLast() {
        if(dbWeather.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(dbWeather.getLast());
    }
}
