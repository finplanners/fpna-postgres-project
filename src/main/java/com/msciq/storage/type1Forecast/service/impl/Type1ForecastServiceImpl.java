package com.msciq.storage.type1Forecast.service.impl;

import com.msciq.storage.model.Type1AForecastingItem;
import com.msciq.storage.type1Forecast.repository.Type1ForecastRepository;
import com.msciq.storage.type1Forecast.service.Type1ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Type1ForecastServiceImpl implements Type1ForecastService {

    @Autowired
    Type1ForecastRepository type1ForecastRepository;

    @Override
    public List<Type1AForecastingItem> save1AForecastingItems(List<Type1AForecastingItem> type1AForecastingItems) {
        return type1ForecastRepository.saveAll(type1AForecastingItems);
    }
}
