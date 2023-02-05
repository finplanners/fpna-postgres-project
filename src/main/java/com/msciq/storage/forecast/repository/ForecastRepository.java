package com.msciq.storage.forecast.repository;

import com.msciq.storage.model.ForecastData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends JpaRepository<ForecastData, Long> {
}
