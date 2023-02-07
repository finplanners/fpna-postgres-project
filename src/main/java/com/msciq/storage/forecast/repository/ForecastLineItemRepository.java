package com.msciq.storage.forecast.repository;

import com.msciq.storage.model.ForecastDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastLineItemRepository extends JpaRepository<ForecastDetails, Long> {
}
