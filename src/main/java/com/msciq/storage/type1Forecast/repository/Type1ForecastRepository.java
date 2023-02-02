package com.msciq.storage.type1Forecast.repository;

import com.msciq.storage.model.Type1AForecastingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type1ForecastRepository extends JpaRepository<Type1AForecastingItem, Long> {
}
