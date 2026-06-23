package com.utn.climalert.repository;

import com.utn.climalert.entity.WeatherRecord;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository {
    WeatherRecord save(WeatherRecord record);
    List<WeatherRecord> findAll();
    Optional<WeatherRecord> findLast();
}
