package com.utn.climalert.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeather(
        @JsonProperty("temp_c") double tempC,
        int humidity
) {}
