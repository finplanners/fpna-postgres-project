package com.msciq.storage.type1Forecast.service;

import com.msciq.storage.model.Type1AForecastingItem;

import java.util.List;

public interface Type1ForecastService {

    /**
     * This method save list of expense type 1A forecast items
     *
     * @param type1AForecastingItems - list of expense type 1A forecast items
     *
     * @return List of Type1AForecastingItem Entity
     * @author Sivaranjani DS
     */
    List<Type1AForecastingItem> save1AForecastingItems(List<Type1AForecastingItem> type1AForecastingItems);
}
