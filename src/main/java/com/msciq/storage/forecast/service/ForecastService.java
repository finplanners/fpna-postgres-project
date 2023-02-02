package com.msciq.storage.forecast.service;

import com.msciq.storage.model.ForecastData;

import java.util.List;

public interface ForecastService {

    /**
     * This method save forecast data
     *
     * @param forecastData
     *
     * @return ForecastData
     * @author Sivaranjani DS
     */
    ForecastData saveForecastData(ForecastData forecastData);
}
